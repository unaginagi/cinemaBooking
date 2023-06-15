package Controller;

import Entity.MovieSession;
import Entity.CinemaRoom;
import Entity.Movie;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateMovieSessionController {
	private final MovieSession movieSession = new MovieSession();
	private final Movie movie = new Movie();
	private final CinemaRoom cinemaRoom = new CinemaRoom();
	
	public boolean validateInput(String input) {
		if(input.isBlank()) {
			return false;
		}
		
		try {
			int num = Integer.parseInt(input);
			
			if(num <= 0)
				return false;
			
		} catch (NumberFormatException e) {
			return false;
		}
		
		return true;
	}
	
	public boolean isDateValid(String date)
	{
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			df.setLenient(false);
			df.parse(date);
			
			return true;
			
		} catch (ParseException e) {
			return false;
		}
	}
	
	public boolean isRoomValid(String id) throws SQLException, Exception
	{
		if(cinemaRoom.getStateCheckData(id).equals("Available"))
			return true;
		
		return false;
	}
	
	public boolean checkMovieId(String input) throws SQLException, Exception {
		ResultSet rs = movie.getMovieIdCheckData(input);
		
		return rs.next();
	}
	
	public boolean checkRoomId(String input) throws SQLException, Exception {
		ResultSet rs = cinemaRoom.getRoomIdCheckData(input);
		
		return rs.next();
	}
	
	public String executeTask(String roomID, String sessionTiming, String movieID) throws SQLException, Exception{
		MovieSession newSession = new MovieSession(roomID, sessionTiming, movieID);
		
		ResultSet[] rsArr = movieSession.getConflictCheckData(newSession);
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");		
		LocalDateTime newData = LocalDateTime.parse(sessionTiming, formatter);
		
		if(rsArr[0].isBeforeFirst() && rsArr[1].isBeforeFirst()) {
			rsArr[0].next();
			rsArr[1].next();
			
			LocalDateTime bef = LocalDateTime.parse(rsArr[0].getString("SessionTiming"), formatter);
			LocalDateTime aft = LocalDateTime.parse(rsArr[1].getString("SessionTiming"), formatter);
			
			System.out.println("Before the checking");
			if(newData.isBefore(bef.plusMinutes(30 + Integer.parseInt(movie.retrieveMovie(rsArr[0].getString("MovieID"))[4]))) || 
			   aft.isBefore(newData.plusMinutes(30 + Integer.parseInt(movie.retrieveMovie(movieID)[4]))))
				return "Conflicting Session";
			else
				return movieSession.addSession(newSession);
			
		} else if(rsArr[0].isBeforeFirst()) {
			rsArr[0].next();
			
			LocalDateTime bef = LocalDateTime.parse(rsArr[0].getString("SessionTiming"), formatter);
			
			if(newData.isBefore(bef.plusMinutes(30 + Integer.parseInt(movie.retrieveMovie(rsArr[0].getString("MovieID"))[4]))))
				return "Conflicting Session";
			else
				return movieSession.addSession(newSession);
			
		} else if(rsArr[1].isBeforeFirst()) {
			rsArr[1].next();
			
			LocalDateTime aft = LocalDateTime.parse(rsArr[1].getString("SessionTiming"), formatter);
			
			if(aft.isBefore(newData.plusMinutes(30 + Integer.parseInt(movie.retrieveMovie(movieID)[4]))))
				return "Conflicting Session";
			else
				return movieSession.addSession(newSession);
			
		} else {
			return movieSession.addSession(newSession);
		}
	}
}
