package Boundary;

import Controller.GetRoomListController;
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

public class GetRoomListBoundary {
	private final GetRoomListController grlc = new GetRoomListController();
	private String roomID;
	
	public String getRoomID() {
		return roomID;
	}
	
	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}
	
	public JPanel constructBoundary(JDialog dialog){
		JPanel panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets= new Insets(15, 15, 15, 15);
		
		Map<JButton, String> ids = new HashMap<>();
		
		try {
			for(String[] data: grlc.executeTask()) {
				JButton roomButton = new JButton(data[1]);
				roomButton.setFont(new Font("Arial", Font.BOLD, 30));
				roomButton.setOpaque(false);
				roomButton.setContentAreaFilled(false);
				roomButton.setBorderPainted(false);
				
				ids.put(roomButton, data[0]);
				
				roomButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent evt) {
						JButton button = (JButton) (evt.getSource());
						roomID = ids.get(button);
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
