package Controller;

import Entity.CinemaRoom;
import Entity.MovieSession;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UpdateCinemaRoomController {
	private final CinemaRoom cinemaRoom = new CinemaRoom();
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
	
	public boolean checkInUseRoom(String id) throws SQLException, Exception {
		ResultSet rsSession = movieSession.getInUseRoomCheckData(id); 
		
		if(rsSession.next())
			return true;
		
		return false;
	}
	
	public String executeTask(String id, String name, int capacity, String state) throws SQLException, Exception {
		CinemaRoom cr = new CinemaRoom(id, name, capacity, state);
		
		ResultSet rsRoom = cinemaRoom.getDuplicateRoomCheckData(cr);
		
		if(rsRoom.next())
			return "Duplicate Cinema Room";
		else
			return cinemaRoom.updateRoom(cr);
	}
}