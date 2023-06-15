package Boundary;

import Controller.RetrieveCinemaRoomController;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class RetrieveCinemaRoomBoundary {
private final RetrieveCinemaRoomController rcrc = new RetrieveCinemaRoomController();
	
	public JPanel constructBoundary(JDialog dialog, String id){
		JPanel panel = new JPanel(new GridBagLayout());
		
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
		
		JLabel nameResultLabel = new JLabel(data[1]);
		nameResultLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		nameResultLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel capacityResultLabel = new JLabel(data[2]);
		capacityResultLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		capacityResultLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel stateResultLabel = new JLabel(data[3]);
		stateResultLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		stateResultLabel.setHorizontalAlignment(JLabel.CENTER);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(idLabel, gbc);
		
		gbc.gridx++;
		panel.add(idResultLabel, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		panel.add(nameLabel, gbc);
		
		gbc.gridx++;
		panel.add(nameResultLabel, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		panel.add(capacityLabel, gbc);
		
		gbc.gridx++;
		panel.add(capacityResultLabel, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		panel.add(stateLabel, gbc);
		
		gbc.gridx++;
		panel.add(stateResultLabel, gbc);
		
		return panel;
	}
}
