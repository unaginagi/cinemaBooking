package Controller;

import Entity.Movie;
import java.sql.SQLException;


public class RetrieveMovieController {
	private final Movie movie = new Movie();
	
	public String[] executeTask(String id) throws SQLException, Exception{
		return movie.retrieveMovie(id);
	}
}
