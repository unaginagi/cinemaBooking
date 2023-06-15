package Controller;

import Entity.CinemaRoom;
import Entity.MovieSession;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DeleteCinemaRoomController {
	private final CinemaRoom cinemaRoom = new CinemaRoom();
	private final MovieSession movieSession = new MovieSession();
	
	public String executeTask(String id) throws SQLException, Exception {
		ResultSet rs = movieSession.getInUseRoomCheckData(id); 
		
		if(rs.next())
			return "Cannot delete in use room\n\nPlease delete the session first";
		else 
			return cinemaRoom.deleteRoom(id);
	}
}
