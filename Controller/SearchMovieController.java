package Controller;

import Entity.Movie;
import java.sql.SQLException;

public class SearchMovieController {
	private final Movie movie = new Movie();
	
	public String[] executeTask(String name) throws SQLException, Exception {
		return movie.searchMovie(name);
	}
}