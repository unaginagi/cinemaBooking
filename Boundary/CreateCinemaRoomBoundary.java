package Boundary;

import Controller.CreateCinemaRoomController;
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

public class CreateCinemaRoomBoundary {
private int num;
	
	private final CreateCinemaRoomController ccrc = new CreateCinemaRoomController();
	
	public JPanel constructBoundary(JDialog dialog){
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets= new Insets(15, 15, 15, 15);
		
		String input = JOptionPane.showInputDialog(null, "Enter the number of cinema room you want to enter");
		
		if(input == null)
			return null;
			
		while(!ccrc.validateInput(input)) {
			JOptionPane.showMessageDialog(null, "Invalid input", "Alert", JOptionPane.WARNING_MESSAGE);
			
			input = JOptionPane.showInputDialog(null, "Enter the number of cinema room you want to enter");
			
			if(null == input)
				return null;
		}
		num = Integer.parseInt(input);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
							
		JLabel capacityLabel = new JLabel("Capacity:");
		capacityLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		capacityLabel.setHorizontalAlignment(JLabel.CENTER);
					
		JLabel stateLabel = new JLabel("State:");
		stateLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		stateLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JTextField nameField = new JTextField(8);
		nameField.setFont(new Font("Arial", Font.PLAIN, 25));
			
		JTextField capacityField = new JTextField(8);
		capacityField.setFont(new Font("Arial", Font.PLAIN, 25));
		
		JComboBox<String> stateCombo = new JComboBox<>(new String[] {"Available", "Not Available (Maintenance)", 
														   "Not Available (Renovation)", "Not Available (Other)"});
		stateCombo.setFont(new Font("Arial", Font.PLAIN, 25));
					
		JButton enterButton = new JButton("Enter");
		enterButton.setFont(new Font("Arial", Font.BOLD, 25));
		enterButton.setPreferredSize(new Dimension(125, 50));
		enterButton.addActionListener(new ActionListener() {
						
			@Override
			public void actionPerformed(ActionEvent evt){
				num--;
				
				if(!ccrc.validateInput(capacityField.getText())) {							
					JOptionPane.showMessageDialog(dialog, "Invalid Capacity input", "Alert", JOptionPane.WARNING_MESSAGE);
								
				} else {
					try {
						JOptionPane.showMessageDialog(dialog, ccrc.executeTask(nameField.getText(), 
													  Integer.parseInt(capacityField.getText()), (String) (stateCombo.getSelectedItem())));
							
						capacityField.setText("");
						nameField.setText("");
						capacityField.setText("");
						stateCombo.setSelectedIndex(0);
											
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
		panel.add(capacityLabel, gbc);
						
		gbc.gridx++;
		panel.add(capacityField, gbc);
					
		gbc.gridx--;
		gbc.gridy++;
		panel.add(stateLabel, gbc);
					
		gbc.gridx++;
		panel.add(stateCombo, gbc);
					
		gbc.gridy++;
		panel.add(enterButton, gbc);
		
		return panel;
	}
}
