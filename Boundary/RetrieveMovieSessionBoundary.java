package Boundary;

import Controller.RetrieveMovieSessionController;
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

public class RetrieveMovieSessionBoundary {
private final RetrieveMovieSessionController rmsc = new RetrieveMovieSessionController();
	
	public JPanel constructBoundary(JDialog dialog, String roomId, String sessionTiming, String movieId){
		JPanel mainPanel = new JPanel(new GridBagLayout());
		JPanel timingPanel = new JPanel(new GridBagLayout());
		JPanel otherPanel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets= new Insets(15, 15, 15, 15);
		
		String[][] data = new String[][] {};
		try {
			data = rmsc.executeTask(roomId, sessionTiming, movieId);
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
						
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);
		}
		
		String[] movieLabelName = new String[] {"ID: ", "Name: ", "Genre: ", "Duration: ", "Description: "};
		String[] roomLabelName = new String[] {"ID: ", "Name: ", "Capacity: ", "State: "};
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		for(String labelName: movieLabelName) {
			JLabel label = new JLabel(labelName);
			label.setFont(new Font("Arial", Font.PLAIN, 25));
			label.setHorizontalAlignment(JLabel.CENTER);
			
			otherPanel.add(label, gbc);
			gbc.gridy++;
		}
		
		gbc.gridx++;
		gbc.gridy = 0;
		for(int i = 0; i < 5; i++) {
			if(i != 2) {
				JLabel label = new JLabel(data[1][i]);
				label.setFont(new Font("Arial", Font.PLAIN, 25));
				label.setHorizontalAlignment(JLabel.CENTER);
				
				otherPanel.add(label, gbc);
				gbc.gridy++;
			}
		}
		
		gbc.gridx++;
		gbc.gridy = 0;
		for(String labelName: roomLabelName) {
			JLabel label = new JLabel(labelName);
			label.setFont(new Font("Arial", Font.PLAIN, 25));
			label.setHorizontalAlignment(JLabel.CENTER);
			
			otherPanel.add(label, gbc);
			gbc.gridy++;
		}
		
		gbc.gridx++;
		gbc.gridy = 0;
		for(String val: data[2]) {
			JLabel label = new JLabel(val);
			label.setFont(new Font("Arial", Font.PLAIN, 25));
			label.setHorizontalAlignment(JLabel.CENTER);
			
			otherPanel.add(label, gbc);
			gbc.gridy++;
		}
		
		JLabel timingLabel = new JLabel("Session Timing: ");
		timingLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		timingLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel timingResultLabel = new JLabel(data[0][1]);
		timingResultLabel.setFont(new Font("Arial", Font.PLAIN, 25));
		timingResultLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel movieLabel = new JLabel("Movie");
		movieLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		movieLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel roomLabel = new JLabel("Cinema Room");
		roomLabel.setFont(new Font("Arial", Font.PLAIN, 30));
		roomLabel.setHorizontalAlignment(JLabel.CENTER);
		
		JTextArea decriptionResultArea = new JTextArea(data[1][2], 5, 11);
		decriptionResultArea.setFont(new Font("Arial", Font.PLAIN, 20));
		decriptionResultArea.setLineWrap(true);
		decriptionResultArea.setWrapStyleWord(true);
		decriptionResultArea.setEditable(false);
		
		JScrollPane descriptionScroll = new JScrollPane(decriptionResultArea);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		otherPanel.add(descriptionScroll, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets.bottom -= 20;
		timingPanel.add(timingLabel, gbc);
		
		gbc.gridx++;
		timingPanel.add(timingResultLabel, gbc);
		
		gbc.gridx--;
		gbc.gridy++;
		gbc.insets.top += 15;
		timingPanel.add(movieLabel, gbc);
		
		gbc.gridx++;
		gbc.insets.left += 140;
		gbc.insets.right -= 10;
		timingPanel.add(roomLabel, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets.left -= 140;
		gbc.insets.top -= 15;
		gbc.insets.bottom += 20;
		gbc.insets.right += 10;
		mainPanel.add(timingPanel, gbc);
		
		gbc.gridy++;
		mainPanel.add(otherPanel, gbc);
		
		return mainPanel;
	}
}