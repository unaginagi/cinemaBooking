package Controller;

import Entity.CinemaRoom;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetRoomListController {
private final CinemaRoom cinemaRoom = new CinemaRoom();
	
	public ArrayList<String[]> executeTask() throws SQLException, Exception{
		return cinemaRoom.getRoomList();
	}
}
