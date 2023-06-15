package Controller;

import Entity.CinemaRoom;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CreateCinemaRoomController {
	private final CinemaRoom cinemaRoom = new CinemaRoom();
	
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
	
	public String executeTask(String name, int capacity, String state) throws SQLException, Exception {
		CinemaRoom cr = new CinemaRoom(name, capacity, state);
		
		ResultSet rs = cinemaRoom.getDuplicateNameCheckData(cr);
		
		if(rs.next())
			return "Duplicate name";
		else
			return cinemaRoom.addRoom(cr);
	}
}