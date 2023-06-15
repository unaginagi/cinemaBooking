package Boundary;

import Controller.RetrieveMovieController;
import Controller.UpdateMovieController;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class UpdateMovieBoundary {
	private final RetrieveMovieController rmc = new RetrieveMovieController();
	private final UpdateMovieController umc = new UpdateMovieController();
	
	public JPanel constructBoundary(JDialog dialog, String id){
		JPanel panel = new JPanel(new GridBagLayout());
		
		try {
			if(umc.checkShowingMovie(id)) {
				JOptionPane.showMessageDialog(dialog, "Cannot update showing movie\n\nPlease delete the session first", "Alert", JOptionPane.WARNING_MESSAGE);
				
				return null;
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);
		}
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets= new Insets(15, 15, 15, 15);
		
		JLabel idLabel = new JLabel("ID:");
		idLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		idLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
					
		JLabel genreLabel = new JLabel("Genre:");
		genreLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		genreLabel.setHorizontalAlignment(JLabel.CENTER);
					
		JLabel durationLabel = new JLabel("Duration:");
		durationLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		durationLabel.setHorizontalAlignment(JLabel.CENTER);
					
		JLabel descriptionLabel = new JLabel("Description:");
		descriptionLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
		
		String[] data = new String[] {};
		try {
			data = rmc.executeTask(id);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);
		}
		
		JLabel idResultLabel = new JLabel(data[0]);
		idResultLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		idResultLabel.setHorizontalAlignment(JLabel.CENTER);
			
		JTextField nameField = new JTextField(data[1], 8);
		nameField.setFont(new Font("Arial", Font.PLAIN, 25));
			
		JTextField genreField = new JTextField(data[3], 8);
		genreField.setFont(new Font("Arial", Font.PLAIN, 25));
					
		JTextField durationField = new JTextField(data[4], 8);
		durationField.setFont(new Font("Arial", Font.PLAIN, 25));
		
		JTextArea descriptionArea = new JTextArea(data[2], 5, 11);
		descriptionArea.setFont(new Font("Arial", Font.PLAIN, 20));
		descriptionArea.setLineWrap(true);
		descriptionArea.setWrapStyleWord(true);
		
		JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setFont(new Font("Arial", Font.BOLD, 25));
		cancelButton.setPreferredSize(new Dimension(125, 50));
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt){
				dialog.setVisible(false);
			}
		});
		
		JButton enterButton = new JButton("Enter");
		enterButton.setFont(new Font("Arial", Font.BOLD, 25));
		enterButton.setPreferredSize(new Dimension(125, 50));
		enterButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent evt){
				if(!umc.validateInput(durationField.getText())) {							
					JOptionPane.showMessageDialog(dialog, "Invalid Duration input", "Alert", JOptionPane.WARNING_MESSAGE);
								
				} else {
					try {
						JOptionPane.showMessageDialog(dialog, umc.executeTask(id, nameField.getText(), descriptionArea.getText(), 
																genreField.getText(), Integer.parseInt(durationField.getText())));
						
						dialog.setVisible(false);
										
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
									
					} catch (Exception e) {
						JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);

					}
				}
			}
		});
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(idLabel, gbc);
		
		gbc.gridx++;
		panel.add(idResultLabel, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		panel.add(nameLabel, gbc);
		
		gbc.gridx++;
		panel.add(nameField, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		panel.add(genreLabel, gbc);
		
		gbc.gridx++;
		panel.add(genreField, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		panel.add(durationLabel, gbc);
		
		gbc.gridx++;
		panel.add(durationField, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		panel.add(descriptionLabel, gbc);
		
		gbc.gridx++;
		panel.add(descriptionScroll, gbc);
		
		gbc.gridy++;
		gbc.gridx--;
		panel.add(cancelButton, gbc);
		
		gbc.gridx++;
		panel.add(enterButton, gbc);
		
		return panel;
	}
}
