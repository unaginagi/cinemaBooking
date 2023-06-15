package Controller;

import Entity.Movie;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetMovieListController {
	private final Movie movie = new Movie();
	
	public ArrayList<String[]> executeTask() throws SQLException, Exception{
		return movie.getMovieList();
	}
}
