package Boundary;

import Controller.CreateMovieController;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

public class CreateMovieBoundary{
	private int num;
	
	private final CreateMovieController cmc = new CreateMovieController();
	
	public JPanel constructBoundary(JDialog dialog){
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets= new Insets(15, 15, 15, 15);
		
		String input = JOptionPane.showInputDialog(dialog, "Enter the number of movies you want to enter");
		
		if(input == null)
			return null;
			
		while(!cmc.validateInput(input)) {
			JOptionPane.showMessageDialog(dialog, "Invalid input", "Alert", JOptionPane.WARNING_MESSAGE);
			
			input = JOptionPane.showInputDialog(dialog, "Enter the number of movies you want to enter");
			
			if(null == input)
				return null;
		}
		num = Integer.parseInt(input);
							
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
			
		JTextField nameField = new JTextField(8);
		nameField.setFont(new Font("Arial", Font.PLAIN, 25));
			
		JTextField genreField = new JTextField(8);
		genreField.setFont(new Font("Arial", Font.PLAIN, 25));
					
		JTextField durationField = new JTextField(8);
		durationField.setFont(new Font("Arial", Font.PLAIN, 25));
		
		JTextArea descriptionArea = new JTextArea(5, 11);
		descriptionArea.setFont(new Font("Arial", Font.PLAIN, 20));
		descriptionArea.setLineWrap(true);
		descriptionArea.setWrapStyleWord(true);
		
		JScrollPane descriptionScroll = new JScrollPane(descriptionArea);

		JButton enterButton = new JButton("Enter");
		enterButton.setFont(new Font("Arial", Font.BOLD, 25));
		enterButton.setPreferredSize(new Dimension(125, 50));
		enterButton.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent evt){
				if(!cmc.validateInput(durationField.getText())) {							
					JOptionPane.showMessageDialog(dialog, "Invalid Duration input", "Alert", JOptionPane.WARNING_MESSAGE);
								
				} else {
					try {
						String result = cmc.executeTask(nameField.getText(), descriptionArea.getText(), 
								genreField.getText(), Integer.parseInt(durationField.getText()));
						
						if(result.equals("Successful")) {
							JOptionPane.showMessageDialog(dialog, result);
							num--;
						} else {
							JOptionPane.showMessageDialog(dialog, result, "Alert", JOptionPane.WARNING_MESSAGE);
						}
						
						nameField.setText("");
						genreField.setText("");
						durationField.setText("");
						descriptionArea.setText("");
										
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
									
					} catch (Exception e) {
						JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);

					}
					
					if(num == 0) 
						dialog.setVisible(false);
					
				}
			}
		});
						
		gbc.gridx = 0;
		gbc.gridy = 0;
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
		panel.add(enterButton, gbc);
		
		return panel;
	}
}