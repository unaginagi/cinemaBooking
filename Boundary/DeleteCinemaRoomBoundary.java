package Boundary;

import Controller.DeleteCinemaRoomController;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class DeleteCinemaRoomBoundary {
private final DeleteCinemaRoomController dcrc = new DeleteCinemaRoomController();
	
	public void constructBoundary(JDialog dialog, String id){		
		try {
			JOptionPane.showMessageDialog(dialog, dcrc.executeTask(id));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);

		}
	}
}
