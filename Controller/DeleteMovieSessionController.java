package Controller;

import Entity.MovieSession;
import java.sql.SQLException;


public class DeleteMovieSessionController {
    private final MovieSession movieSession = new MovieSession();
	
	public String executeTask(String roomID, String sessionTiming) throws SQLException, Exception{
		return movieSession.deleteSession(roomID, sessionTiming);
	}
}
