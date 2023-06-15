package Controller;

import Entity.Movie;
import Entity.MovieSession;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UpdateMovieController {
	private final Movie movie = new Movie();
	private final MovieSession movieSession = new MovieSession();
	
	public boolean validateInput(String input) {
		try {
			int num = Integer.parseInt(input);
			
			if(num <= 0)
				return false;
			
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean checkShowingMovie(String id) throws SQLException, Exception{
		ResultSet rsSession = movieSession.getShowingMovieCheckData(id);
	
		if(rsSession.next())
			return true;
		
		return false;
	}
	
	public String executeTask(String id, String name, String description, String genre, int duration) throws SQLException, Exception {
		Movie m = new Movie(id, name, description, genre, duration);
		
		ResultSet rsMovie = movie.getDuplicateMovieCheckData(m);
		
		if(rsMovie.next())
			return "Duplicate Entry";
		else
			return movie.updateMovie(m);
	}
}
