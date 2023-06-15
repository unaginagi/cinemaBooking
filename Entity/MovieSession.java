package Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MovieSession{
	private String roomID;
	private String sessionTiming;
	private String movieID;
	
	public MovieSession() {}
	
	public MovieSession(String roomID, String sessionTiming, String movieID) {
		this.roomID = roomID;
		this.sessionTiming = sessionTiming;
		this.movieID = movieID;
	}
	
	public MovieSession(String sessionTiming) {
		this(null, sessionTiming, null);
	}
	
	public MovieSession(MovieSession ms) {
		this(ms.roomID, ms.sessionTiming, ms.movieID);
	}

	public String getMovieID() {
		return movieID;
	}

	public void setMovieID(String movieID) {
		this.movieID = movieID;
	}

	public String getRoomID() {
		return roomID;
	}

	public void setRoomID(String roomID) {
		this.roomID = roomID;
	}

	public String getSessionTiming() {
		return sessionTiming;
	}

	public void setSessionTiming(String sessionTiming) {
		this.sessionTiming = sessionTiming;
	}
	
        final String url = "jdbc:mysql://localhost:3306/cinemabooking";
        final String username = "root";
        final String dbpassword = "password";
        
	public String addSession(MovieSession ms) throws SQLException, Exception {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    stmt.executeUpdate("INSERT INTO MovieSession "
				+ "values (" + ms.roomID + ", '" 
				+ ms.sessionTiming + "', " 
				+ ms.movieID + ")");
	         
	    stmt.close();
	    conn.close();
	    
	    return "Successful";
	}
	
	public String[] retrieveSession(String roomID, String sessionTiming) throws SQLException, Exception{
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		 
	    Statement stmt = conn.createStatement();
	         
	    ResultSet rs = stmt.executeQuery("SELECT * FROM MovieSession"
		  		   + " WHERE RoomID = " + roomID
		  		   + " AND SessionTiming = '" + sessionTiming + "'");

		rs.next();

		return new String[] {rs.getString("RoomID"), rs.getString("SessionTiming"), rs.getString("MovieID")};
	}
	
	public String updateSession(MovieSession ms) throws SQLException, Exception {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    stmt.executeUpdate("UPDATE MovieSession "
     	 		+ "SET MovieID = " + ms.movieID
     	 		+ " WHERE RoomID = " + ms.roomID
     	 		+ " AND SessionTiming = " + ms.sessionTiming);
	         
	    stmt.close();
	    conn.close();
	    
	    return "Successful";
	}
	
	public String deleteSession(String roomID, String sessionTiming) throws SQLException, Exception {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    stmt.executeUpdate("DELETE FROM MovieSession "
			 	+ "WHERE RoomID = " + roomID 
			 	+ " AND SessionTiming = '" + sessionTiming + "'");
	         
	    stmt.close();
	    conn.close();
	    
	    return "Successful";
	}
	
	public ArrayList<String[]> searchSession(String movieID) throws SQLException, Exception{	         
	    ArrayList<String[]> sessionArr = new ArrayList<>();
		
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		 
	    Statement stmt = conn.createStatement();
	         
	    ResultSet rs = stmt.executeQuery("SELECT * FROM MovieSession "
  			   + "WHERE movieID = " + movieID);
	    
	    while(rs.next())
		    sessionArr.add(new String[]{rs.getString("RoomID"), rs.getString("SessionTiming"), rs.getString("MovieID")});
	    
	    return sessionArr;
	}
	
	public ResultSet[] getConflictCheckData(MovieSession ms) throws SQLException, Exception{
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		 
	    Statement stmtBef = conn.createStatement();
	    Statement stmtAft = conn.createStatement();
		
		ResultSet rsBef = stmtBef.executeQuery("SELECT MovieID, SessionTiming "
				+ "FROM MovieSession "
				+ "WHERE SessionTiming <= '" + ms.sessionTiming + "' "
				+ "AND RoomID = " + ms.roomID + " "
				+ "ORDER BY SessionTiming DESC "
				+ "LIMIT 1");
		
		ResultSet rsAft = stmtAft.executeQuery("SELECT MovieID, SessionTiming "
				+ "FROM MovieSession "
				+ "WHERE SessionTiming >= '" + ms.sessionTiming + "' "
				+ "AND RoomID = " + ms.roomID + " "
				+ "ORDER BY SessionTiming ASC "
				+ "LIMIT 1");
		
		return new ResultSet[]{rsBef, rsAft};
	}
	
	public ResultSet getShowingMovieCheckData(String id) throws SQLException, Exception{
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		 
	    Statement stmt = conn.createStatement();
		
		return stmt.executeQuery("SELECT MovieID FROM MovieSession "
				   + "WHERE MovieID = " + id);
	}
	
	public ResultSet getInUseRoomCheckData(String id) throws SQLException, Exception{
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		 
	    Statement stmt = conn.createStatement();
		
		return stmt.executeQuery("SELECT RoomID FROM MovieSession "
				   + "WHERE RoomID = " + id);
	}
	
	public ArrayList<String[]> getSessionList() throws SQLException, Exception{
            ArrayList<String[]> sessionArr = new ArrayList<>();
		
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		 
	    Statement stmt = conn.createStatement();

	    ResultSet rs = stmt.executeQuery("SELECT * FROM MovieSession");
	    
	    while(rs.next())
	    	sessionArr.add(new String[] {rs.getString("RoomID"), rs.getString("SessionTiming"), rs.getString("MovieID")});
	    
	    return sessionArr;
	}
	
	@Override
	public String toString() {
		return String.format("Movie ID: %s%n"
						   + "Room ID: %s%n"
						   + "Session Timing: %s%n", this.movieID, this.roomID, this.sessionTiming);
	}
	
}