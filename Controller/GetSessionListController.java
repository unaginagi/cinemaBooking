package Controller;

import Entity.MovieSession;
import java.sql.SQLException;
import java.util.ArrayList;


public class GetSessionListController {
private final MovieSession movieSession = new MovieSession();
	
	public ArrayList<String[]> executeTask() throws SQLException, Exception{
		return movieSession.getSessionList();
	}
}
