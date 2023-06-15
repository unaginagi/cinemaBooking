package Boundary;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

public class MovieListingGUI extends JFrame implements ActionListener{
	private final GetMovieListBoundary gmlb = new GetMovieListBoundary();
	private final CreateMovieBoundary cmb = new CreateMovieBoundary();
	private final RetrieveMovieBoundary rmb = new RetrieveMovieBoundary();
	private final UpdateMovieBoundary umb = new UpdateMovieBoundary();
	private final DeleteMovieBoundary dmb = new DeleteMovieBoundary();
	private final SearchMovieBoundary smb = new SearchMovieBoundary();
	
	private final GetRoomListBoundary grlb = new GetRoomListBoundary();
	private final CreateCinemaRoomBoundary ccrb = new CreateCinemaRoomBoundary();
	private final RetrieveCinemaRoomBoundary rcrb = new RetrieveCinemaRoomBoundary();
	private final UpdateCinemaRoomBoundary ucrb = new UpdateCinemaRoomBoundary();
	private final DeleteCinemaRoomBoundary dcrb = new DeleteCinemaRoomBoundary();
	private final SearchCinemaRoomBoundary scrb = new SearchCinemaRoomBoundary();
	
	private final GetSessionListBoundary gslb = new GetSessionListBoundary();
	private final CreateMovieSessionBoundary cmsb = new CreateMovieSessionBoundary();
	private final RetrieveMovieSessionBoundary rmsb = new RetrieveMovieSessionBoundary();
	private final UpdateMovieSessionBoundary umsb = new UpdateMovieSessionBoundary();
	private final DeleteMovieSessionBoundary dmsb = new DeleteMovieSessionBoundary();
	private final SearchMovieSessionBoundary smsb = new SearchMovieSessionBoundary();
	
        public MovieListingGUI() {
            // Initialize the UI components and configure the frame
            setTitle("Movie Listings Options");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(800, 600);
            setLocationRelativeTo(null);
            setResizable(false);
            initComponents();
        }
        
	private void initComponents(){
            
                JButton logoutButton = new JButton("Back");
                logoutButton.addActionListener(this);
                
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets= new Insets(15, 15, 15, 15);
		
		JComboBox<String> dataCombo = new JComboBox<>(new String[] {"Movie", "Cinema Room", "Movie Session"});
		dataCombo.setFont(new Font("Arial", Font.PLAIN, 25));
		
		dataCombo.getSelectedItem();
		
		JLabel comboLabel = new JLabel("Choose The Data Type: ");
		comboLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		comboLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JButton createButton = new JButton("Create");
		createButton.setFont(new Font("Arial", Font.BOLD, 25));
		createButton.setPreferredSize(new Dimension(225, 65));
		
		createButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
                            Frame frame = null;
				JDialog dialog = new JDialog(frame);
				dialog.setModal(true);
				dialog.setSize(600, 600);
				dialog.setLocationRelativeTo(null);
				dialog.setAlwaysOnTop(true);
				
				JPanel panel = null;
				switch(dataCombo.getSelectedIndex()) {
					case 0:
					{
						dialog.setTitle("Create Movie");
						panel = cmb.constructBoundary(dialog);
						break;
					}
						
					case 1:
					{
						dialog.setTitle("Create Cinema Room");
						panel = ccrb.constructBoundary(dialog);
						break;
					}
						
					case 2:
					{
						dialog.setTitle("Create Movie Session");
						panel = cmsb.constructBoundary(dialog);
						break;
					}	
				}
				
				if(panel != null) {
					dialog.add(panel);
					dialog.pack();
					dialog.setVisible(true);
				}
			}
		});
		
		JButton retrieveButton = new JButton("Retrieve");
		retrieveButton.setFont(new Font("Arial", Font.BOLD, 25));
		retrieveButton.setPreferredSize(new Dimension(225, 65));
		
		retrieveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
                            Frame frame = null;
				JDialog dialog = new JDialog(frame);
				dialog.setModal(true);
				dialog.setSize(600, 600);
				dialog.setLocationRelativeTo(null);
				dialog.setAlwaysOnTop(true);
				
				switch(dataCombo.getSelectedIndex()) {
					case 0:
					{
						dialog.setTitle("Retrieve Movie");
						
						JScrollPane scrollPane = new JScrollPane(gmlb.constructBoundary(dialog));
						dialog.add(scrollPane, BorderLayout.CENTER);
						
						dialog.setVisible(true);
						
						dialog.getContentPane().removeAll();
						
						if(gmlb.getMovieID() != null)
							dialog.add(rmb.constructBoundary(dialog, gmlb.getMovieID()));
						
						gmlb.setMovieID(null);
						break;
					}
					
					case 1:
					{
						dialog.setTitle("Retrieve Cinema Room");
						
						JScrollPane scrollPane = new JScrollPane(grlb.constructBoundary(dialog));
						dialog.add(scrollPane, BorderLayout.CENTER);
						
						dialog.setVisible(true);
						
						dialog.getContentPane().removeAll();
						
						if(grlb.getRoomID() != null)	
							dialog.add(rcrb.constructBoundary(dialog, grlb.getRoomID()));
						
						grlb.setRoomID(null);
						
						break;
					}
					
					case 2:
					{
						dialog.setTitle("Retrieve Movie Session");
						
						JScrollPane scrollPane = new JScrollPane(gslb.constructBoundary(dialog));
						dialog.add(scrollPane, BorderLayout.CENTER);
						
						dialog.setVisible(true);
						
						dialog.getContentPane().removeAll();
						
						if(gslb.getSessionTiming() != null)	
							dialog.add(rmsb.constructBoundary(dialog, gslb.getRoomId(), gslb.getSessionTiming(), gslb.getMovieId()));
						
						gslb.setMovieId(null);
						gslb.setRoomId(null);
						gslb.setSessionTiming(null);
						
						break;
					}
				}
				
				if(dialog.getContentPane().getComponentCount() > 0) {
					dialog.revalidate();
					dialog.repaint();
					dialog.pack();
					
					dialog.setVisible(true);
				}
			}
		});
		
		JButton updateButton = new JButton("Update");
		updateButton.setFont(new Font("Arial", Font.BOLD, 25));
		updateButton.setPreferredSize(new Dimension(225, 65));
		
		updateButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
                            Frame frame = null;
				JDialog dialog = new JDialog(frame);
				dialog.setModal(true);
				dialog.setSize(600, 600);
				dialog.setLocationRelativeTo(null);
				dialog.setAlwaysOnTop(true);
				
				JPanel panel = null;
				switch(dataCombo.getSelectedIndex()) {
					case 0:
					{
						dialog.setTitle("Update Movie");
						
						JScrollPane scrollPane = new JScrollPane(gmlb.constructBoundary(dialog));	
						dialog.add(scrollPane, BorderLayout.CENTER);
							
						dialog.setVisible(true);
						
						dialog.getContentPane().removeAll();
						
						if(gmlb.getMovieID() != null)
							panel = umb.constructBoundary(dialog, gmlb.getMovieID());
						
						if(panel != null)
							dialog.add(panel);
							
						gmlb.setMovieID(null);
						
						break;
					}
					
					case 1:
					{
						dialog.setTitle("Update Cinema Room");
						
						JScrollPane scrollPane = new JScrollPane(grlb.constructBoundary(dialog));	
						dialog.add(scrollPane, BorderLayout.CENTER);
							
						dialog.setVisible(true);
						
						dialog.getContentPane().removeAll();
						
						if(grlb.getRoomID() != null)
							panel = ucrb.constructBoundary(dialog, grlb.getRoomID());
						
						if(panel != null)
							dialog.add(panel);
							
						grlb.setRoomID(null);
						
						break;
					}
					
					case 2:
					{
						dialog.setTitle("Update Movie Session");
						
						JScrollPane scrollPane = new JScrollPane(gslb.constructBoundary(dialog));
						dialog.add(scrollPane, BorderLayout.CENTER);
						
						dialog.setVisible(true);
						
						dialog.getContentPane().removeAll();
						
						if(gslb.getSessionTiming() != null)
							panel = umsb.constructBoundary(dialog, gslb.getRoomId(), gslb.getSessionTiming(), gslb.getMovieId());
						
						if(panel != null)
							dialog.add(panel);
						
						gslb.setMovieId(null);
						gslb.setRoomId(null);
						gslb.setSessionTiming(null);
						
						break;
					}
				}
				
				if(dialog.getContentPane().getComponentCount() > 0) {
					dialog.revalidate();
					dialog.repaint();
					dialog.pack();
					
					dialog.setVisible(true);
				}
			}
		});
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setFont(new Font("Arial", Font.BOLD, 25));
		deleteButton.setPreferredSize(new Dimension(225, 65));
		
		deleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
                            Frame frame = null;
				JDialog dialog = new JDialog(frame);
				dialog.setModal(true);
				dialog.setSize(600, 600);
				dialog.setLocationRelativeTo(null);
				dialog.setAlwaysOnTop(true);
				
				switch(dataCombo.getSelectedIndex()) {
					case 0:
					{
						dialog.setTitle("Delete Movie");
						
						JScrollPane scrollPane = new JScrollPane(gmlb.constructBoundary(dialog));
						
						dialog.add(scrollPane, BorderLayout.CENTER);
							
						dialog.setVisible(true);
						
						if(gmlb.getMovieID() != null)
							dmb.constructBoundary(dialog, gmlb.getMovieID());
						
						gmlb.setMovieID(null);
						
						break;
					}
					
					case 1:
					{
						dialog.setTitle("Delete Cinema Room");
						
						JScrollPane scrollPane = new JScrollPane(grlb.constructBoundary(dialog));
						
						dialog.add(scrollPane, BorderLayout.CENTER);
							
						dialog.setVisible(true);
						
						if(grlb.getRoomID() != null)
							dcrb.constructBoundary(dialog, grlb.getRoomID());
						
						grlb.setRoomID(null);
						
						break;
					}
					
					case 2:
					{
						dialog.setTitle("Delete Movie Session");
						
						JScrollPane scrollPane = new JScrollPane(gslb.constructBoundary(dialog));
						dialog.add(scrollPane, BorderLayout.CENTER);
						
						dialog.setVisible(true);
						
						dialog.getContentPane().removeAll();
						
						if(gslb.getSessionTiming() != null)
							dmsb.constructBoundary(dialog, gslb.getRoomId(), gslb.getSessionTiming());
						
						gslb.setMovieId(null);
						gslb.setRoomId(null);
						gslb.setSessionTiming(null);
						
						break;
					}
				}

				
			}
		});
		
		JButton searchButton = new JButton("Search");
		searchButton.setFont(new Font("Arial", Font.BOLD, 25));
		searchButton.setPreferredSize(new Dimension(225, 65));
		
		searchButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
                            Frame frame = null;
				JDialog dialog = new JDialog(frame);
				dialog.setModal(true);
				dialog.setSize(600, 600);
				dialog.setLocationRelativeTo(null);
				dialog.setAlwaysOnTop(true);
				
				JPanel panel = null;
				switch(dataCombo.getSelectedIndex()) {
					case 0:
					{
						dialog.setTitle("Search Movie");
						panel = smb.constructBoundary(dialog);
						break;
					}
					
					case 1:
					{
						dialog.setTitle("Search Cinema Room");
						panel = scrb.constructBoundary(dialog);
						break;
					}
					
					case 2:
					{
						dialog.setTitle("Search Movie Session");
						panel = smsb.constructBoundary(dialog);
						break;
					}
				}
				
				if(panel != null) {
					dialog.add(panel);
					dialog.pack();
					dialog.setVisible(true);
				}
			}
		});
		
		JButton viewAllButton = new JButton("View All");
		viewAllButton.setFont(new Font("Arial", Font.BOLD, 25));
		viewAllButton.setPreferredSize(new Dimension(225, 65));
		
		viewAllButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt) {
                            Frame frame = null;
				JDialog dialog = new JDialog(frame);
				dialog.setModal(true);
				dialog.setSize(600, 600);
				dialog.setLocationRelativeTo(null);
				dialog.setAlwaysOnTop(true);
				
				switch(dataCombo.getSelectedIndex()) {
					case 0:
					{
						dialog.setTitle("All Movie");
						
						JScrollPane scrollPane = new JScrollPane(gmlb.constructBoundary(dialog));	
						
						dialog.add(scrollPane, BorderLayout.CENTER);
							
						dialog.setVisible(true);
						
						gmlb.setMovieID(null);
						
						break;
					}
					
					case 1:
					{
						dialog.setTitle("All Cinema Room");
						
						JScrollPane scrollPane = new JScrollPane(grlb.constructBoundary(dialog));
						
						dialog.add(scrollPane, BorderLayout.CENTER);
							
						dialog.setVisible(true);
						
						grlb.setRoomID(null);
						
						break;
					}
					
					case 2:
					{
						dialog.setTitle("All Movie Session");
						
						JScrollPane scrollPane = new JScrollPane(gslb.constructBoundary(dialog));
						dialog.add(scrollPane, BorderLayout.CENTER);
						
						dialog.setVisible(true);
						
						gslb.setMovieId(null);
						gslb.setRoomId(null);
						gslb.setSessionTiming(null);
						
						break;
					}
				}
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(comboLabel, gbc);
		
		gbc.gridx++;
		panel.add(dataCombo, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		gbc.insets.top += 35;
		panel.add(createButton, gbc);
		
		gbc.gridx++;
		panel.add(retrieveButton, gbc);
		
		gbc.gridy++;
		gbc.gridx--;
		gbc.insets.top -= 35;
		panel.add(updateButton, gbc);
		
		gbc.gridx++;
		panel.add(deleteButton, gbc);
		
		gbc.gridy++;
		gbc.gridx--;
		panel.add(searchButton, gbc);
		
		gbc.gridx++;
		panel.add(viewAllButton, gbc);
                
                gbc.gridx++;
		panel.add(logoutButton, gbc);
		
		add(panel);
	}

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new DashboardCinemaManager().setVisible(true);
                }
                });
                dispose();
        }else{
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}