package Controller;

import Entity.Movie;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CreateMovieController {
	private final Movie movie = new Movie();
	
        public CreateMovieController(){
        
        }
        
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
	
	public String executeTask(String name, String description, String genre, int duration) throws SQLException, Exception {
		Movie m = new Movie(name, description, genre, duration);
		
		ResultSet rs = movie.getDuplicateMovieCheckData(m);
		
		if(rs.next())
			return "Duplicate Movie";
		else
			return movie.addMovie(m);
	}
}