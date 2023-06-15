package Boundary;

import Controller.DeleteMovieSessionController;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class DeleteMovieSessionBoundary {
private final DeleteMovieSessionController dmsc = new DeleteMovieSessionController();
	
	public void constructBoundary(JDialog dialog, String roomId, String sessionTiming){		
		try {
			JOptionPane.showMessageDialog(dialog, dmsc.executeTask(roomId, sessionTiming));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);

		}
	}
}
