package Boundary;

import Controller.SearchMovieController;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class SearchMovieBoundary {
	private final SearchMovieController smc = new SearchMovieController();
	
	public JPanel constructBoundary(JDialog dialog){
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets= new Insets(15, 15, 15, 15);
		
		String input = JOptionPane.showInputDialog(dialog, "Enter the movie name");
		
		if(input == null)
			return null;
			
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
			data = smc.executeTask(input);
			
			if(data.length == 0) {
				JOptionPane.showMessageDialog(dialog, "Movie does not exist", "Alert", JOptionPane.WARNING_MESSAGE);
				dialog.setVisible(false);
				
				return null;
			}
			
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
		
		JLabel genreResultLabel = new JLabel(data[3]);
		genreResultLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		genreResultLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel durationResultLabel = new JLabel(data[4]);
		durationResultLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		durationResultLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JTextArea decriptionResultArea = new JTextArea(data[2], 5, 11);
		decriptionResultArea.setFont(new Font("Arial", Font.PLAIN, 20));
		decriptionResultArea.setLineWrap(true);
		decriptionResultArea.setWrapStyleWord(true);
		decriptionResultArea.setEditable(false);
		
		JScrollPane descriptionScroll = new JScrollPane(decriptionResultArea);
		
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
		panel.add(genreLabel, gbc);
		
		gbc.gridx++;
		panel.add(genreResultLabel, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		panel.add(durationLabel, gbc);
		
		gbc.gridx++;
		panel.add(durationResultLabel, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		panel.add(descriptionLabel, gbc);
		
		gbc.gridx++;
		panel.add(descriptionScroll, gbc);
		
		return panel;
	}
}
