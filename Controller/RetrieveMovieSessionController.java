package Controller;

import Entity.CinemaRoom;
import Entity.Movie;
import Entity.MovieSession;
import java.sql.SQLException;


public class RetrieveMovieSessionController {
	private final MovieSession movieSession = new MovieSession();
	private final Movie movie = new Movie();
	private final CinemaRoom cinemaRoom = new CinemaRoom();

	public String[][] executeTask(String roomID, String sessionTiming, String movieID) throws SQLException, Exception{
		return new String[][]{movieSession.retrieveSession(roomID, sessionTiming), 
							movie.retrieveMovie(movieID), 
							cinemaRoom.retrieveRoom(roomID)};
	}
}