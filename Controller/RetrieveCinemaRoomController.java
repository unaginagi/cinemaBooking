package Controller;

import Entity.CinemaRoom;
import java.sql.SQLException;


public class RetrieveCinemaRoomController {
	private final CinemaRoom cinemaRoom = new CinemaRoom();
	
	public String[] executeTask(String id) throws SQLException, Exception{
		return cinemaRoom.retrieveRoom(id);
	}
}
