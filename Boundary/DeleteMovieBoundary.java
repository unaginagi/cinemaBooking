package Boundary;

import Controller.DeleteMovieController;
import java.sql.SQLException;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class DeleteMovieBoundary {
	private final DeleteMovieController dmc = new DeleteMovieController();
	
	public void constructBoundary(JDialog dialog, String id){		
		try {
			JOptionPane.showMessageDialog(dialog, dmc.executeTask(id));
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(dialog, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(dialog, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);

		}
	}
}
