package Controller;

import Entity.Movie;
import Entity.MovieSession;
import java.sql.SQLException;
import java.util.ArrayList;


public class SearchMovieSessionController {
	private final MovieSession movieSession = new MovieSession();
	private final Movie movie = new Movie();
	
	public ArrayList<String[]> executeTask(String movieName) throws SQLException, Exception{
		return movieSession.searchSession(movie.searchMovie(movieName)[0]);
	}
}