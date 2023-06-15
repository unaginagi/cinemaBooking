package Boundary;

import Controller.GetMovieListController;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


public class GetMovieListBoundary{
	private final GetMovieListController gmlc = new GetMovieListController();
	private String movieID;
	
	public String getMovieID() {
		return movieID;
	}
	
	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}
	
	public JPanel constructBoundary(JDialog dialog){
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets= new Insets(15, 15, 15, 15);
		
		Map<JButton, String> ids = new HashMap<>();
		
		try {
			for(String[] data: gmlc.executeTask()) {
				JButton movieButton = new JButton(data[1]);
				movieButton.setFont(new Font("Arial", Font.BOLD, 30));
				movieButton.setOpaque(false);
				movieButton.setContentAreaFilled(false);
				movieButton.setBorderPainted(false);
				
				ids.put(movieButton, data[0]);
				
				movieButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						JButton button = (JButton) (evt.getSource());
						movieID = ids.get(button);
						dialog.setVisible(false);
					}
				});
				
				gbc.gridy++;
				panel.add(movieButton, gbc);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
						
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);

		}
		
		return panel;
	}
}
