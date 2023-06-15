package Controller;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Entity.Movie;
import Entity.MovieSession;

public class UpdateMovieSessionController {
	private final MovieSession movieSession = new MovieSession();
	private final Movie movie = new Movie();
	
	public boolean checkMovieId(String input) throws SQLException, Exception {
		ResultSet rs = movie.getMovieIdCheckData(input);
		
		return rs.next();
	}
	
	public String executeTask(String roomID, String sessionTiming, String newMovieID, String oldMovieID) throws SQLException, Exception{
		movieSession.deleteSession(roomID, sessionTiming);
		
		MovieSession oldSession = new MovieSession(roomID, sessionTiming, oldMovieID);
		MovieSession newSession = new MovieSession(roomID, sessionTiming, newMovieID);
		
		ResultSet[] rsArr = movieSession.getConflictCheckData(newSession);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");		
		LocalDateTime newData = LocalDateTime.parse(sessionTiming, formatter);
		
		if(rsArr[0].isBeforeFirst() && rsArr[1].isBeforeFirst()) {
			rsArr[0].next();
			rsArr[1].next();
			
			LocalDateTime bef = LocalDateTime.parse(rsArr[0].getString("SessionTiming"), formatter);
			LocalDateTime aft = LocalDateTime.parse(rsArr[1].getString("SessionTiming"), formatter);
			
			if(newData.isBefore(bef.plusMinutes(30 + Integer.parseInt(movie.retrieveMovie(rsArr[0].getString("MovieID"))[4]))) || 
			   aft.isBefore(newData.plusMinutes(30 + Integer.parseInt(movie.retrieveMovie(newMovieID)[4])))) {
				movieSession.addSession(oldSession);
				
				return "Conflicting Session";
			} else {
				return movieSession.addSession(newSession);
			}
			
		} else if(rsArr[0].isBeforeFirst()) {
			rsArr[0].next();
			
			LocalDateTime bef = LocalDateTime.parse(rsArr[0].getString("SessionTiming"), formatter);
			
			if(newData.isBefore(bef.plusMinutes(30 + Integer.parseInt(movie.retrieveMovie(rsArr[0].getString("MovieID"))[4])))) {
				movieSession.addSession(oldSession);
				
				return "Conflicting Session";
			} else {
				return movieSession.addSession(newSession);
			}
			
		} else if(rsArr[1].isBeforeFirst()) {
			rsArr[1].next();
			
			LocalDateTime aft = LocalDateTime.parse(rsArr[1].getString("SessionTiming"), formatter);
			
			if(aft.isBefore(newData.plusMinutes(30 + Integer.parseInt(movie.retrieveMovie(newMovieID)[4])))) {
				movieSession.addSession(oldSession);
				
				return "Conflicting Session";
			} else {
				return movieSession.addSession(newSession);
			}	
		} else {
			return movieSession.addSession(newSession);
		}
	}
}