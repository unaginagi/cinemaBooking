package Boundary;

import Controller.RetrieveCinemaRoomController;
import Controller.UpdateCinemaRoomController;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class UpdateCinemaRoomBoundary {
	private final RetrieveCinemaRoomController rcrc = new RetrieveCinemaRoomController();
	private final UpdateCinemaRoomController ucrc = new UpdateCinemaRoomController();
	
	public JPanel constructBoundary(JDialog dialog, String id){
		JPanel panel = new JPanel(new GridBagLayout());
		
		try {
			if(ucrc.checkInUseRoom(id)) {
				JOptionPane.showMessageDialog(dialog, "Cannot update in use room\n\nPlease delete the session first", "Alert", JOptionPane.WARNING_MESSAGE);
				
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
					
		JLabel capacityLabel = new JLabel("Capacity:");
		capacityLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		capacityLabel.setHorizontalAlignment(JLabel.CENTER);
					
		JLabel stateLabel = new JLabel("State:");
		stateLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		stateLabel.setHorizontalAlignment(JLabel.CENTER);
		
		String[] data = new String[] {};
		try {
			data = rcrc.executeTask(id);
			
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
			
		JTextField capacityField = new JTextField(data[2], 8);
		capacityField.setFont(new Font("Arial", Font.PLAIN, 25));
					
		JComboBox<String> stateCombo = new JComboBox<>(new String[] {"Available", "Not Available (Maintenance)", 
				   "Not Available (Renovation)", "Not Available (Other)"});
		stateCombo.setFont(new Font("Arial", Font.PLAIN, 25));
		stateCombo.setSelectedItem(data[3]);
		
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
				if(!ucrc.validateInput(capacityField.getText())) {							
					JOptionPane.showMessageDialog(dialog, "Invalid Capacity input", "Alert", JOptionPane.WARNING_MESSAGE);
								
				} else {
					try {
						JOptionPane.showMessageDialog(dialog, ucrc.executeTask(id, nameField.getText(), 
													 Integer.parseInt(capacityField.getText()), (String) (stateCombo.getSelectedItem())));
							
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
		panel.add(capacityLabel, gbc);
		
		gbc.gridx++;
		panel.add(capacityField, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		panel.add(stateLabel, gbc);
		
		gbc.gridx++;
		panel.add(stateCombo, gbc);
		
		gbc.gridy++;
		gbc.gridx--;
		panel.add(cancelButton, gbc);
		
		gbc.gridx++;
		panel.add(enterButton, gbc);
		
		return panel;
	}
}