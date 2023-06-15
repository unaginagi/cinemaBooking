package Boundary;

import Controller.GetSessionListController;
import Controller.RetrieveCinemaRoomController;
import Controller.RetrieveMovieController;
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

public class GetSessionListBoundary {
	private final GetSessionListController gslc = new GetSessionListController();
	private final RetrieveCinemaRoomController rcrc = new RetrieveCinemaRoomController();
	private final RetrieveMovieController rmc = new RetrieveMovieController();
	
	private String roomId;
	private String sessionTiming;
	private String movieId;
	
	public String getRoomId() {
		return roomId;
	}
	
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
	public String getSessionTiming() {
		return sessionTiming;
	}

	public void setSessionTiming(String sessionTiming) {
		this.sessionTiming = sessionTiming;
	}
	
	public String getMovieId() {
		return movieId;
	}

	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}

	public JPanel constructBoundary(JDialog dialog){
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets= new Insets(15, 15, 15, 15);
		
		Map<JButton, String[]> ids = new HashMap<>();
		
		try {			
			for(String[] data: gslc.executeTask()) {
				String text = rmc.executeTask(data[2])[1] + " \\n" + rcrc.executeTask(data[0])[1] + " " + data[1];
				
				JButton roomButton = new JButton("<html>" + "<center>" + text.replaceAll("\\\\n", "<br>") + "</center>" + "</html>");
				roomButton.setFont(new Font("Arial", Font.BOLD, 30));
				roomButton.setOpaque(false);
				roomButton.setContentAreaFilled(false);
				roomButton.setBorderPainted(false);
				
				ids.put(roomButton, new String[]{data[0], data[1], data[2]});
				
				roomButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						JButton button = (JButton) (evt.getSource());
						roomId = ids.get(button)[0];
						sessionTiming = ids.get(button)[1];
						movieId = ids.get(button)[2];
						dialog.setVisible(false);
					}
				});
				
				gbc.gridy++;
				panel.add(roomButton, gbc);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
						
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);

		}
		
		return panel;
	}
}
