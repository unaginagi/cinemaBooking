package Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CinemaRoom{
	private String id;
	private String name;
	private int capacity;
	private String state;
	
	public CinemaRoom() {}
	
	public CinemaRoom(String id, String name, int capacity, String state) {
		this.id = id;
		this.name = name;
		this.capacity = capacity;
		this.state = state;
	}
	
	public CinemaRoom(String name, int capacity, String state) {
		this(null, name, capacity, state);
	}
	
	public CinemaRoom(CinemaRoom cr) {
		this(cr.id, cr.name, cr.capacity, cr.state);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
        final String url = "jdbc:mysql://localhost:3306/cinemabooking";
        final String username = "root";
        final String dbpassword = "password";
        
	public String addRoom(CinemaRoom cr) throws SQLException, Exception {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    stmt.executeUpdate("INSERT INTO CinemaRoom (Name, Capacity, State)"
					+ "values ('" + cr.name
					+ "', " + cr.capacity
					+ ", '" + cr.state + "')");
	    
	    return "Successful";
	}
	
	public String[] retrieveRoom(String id) throws SQLException, Exception {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
		
	    ResultSet rs = stmt.executeQuery("SELECT * FROM CinemaRoom "
	         			   + "WHERE ID = " + id);
	    
	    rs.next();
	    
	    return new String[] {rs.getString("ID"), rs.getString("Name"), rs.getString("Capacity"), rs.getString("State")};
	}
	
	public String updateRoom(CinemaRoom cr) throws SQLException, Exception {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    stmt.executeUpdate("UPDATE CinemaRoom "
	    			+ "SET Name = '" + cr.name
	    			+ "', Capacity = " + cr.capacity
	    			+ ", State = '" + cr.state
	    			+ "' WHERE ID = " + cr.id);
	    
	    return "Successful";
	}
	
	public String deleteRoom(String id) throws SQLException, Exception {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
		
	    stmt.executeUpdate("DELETE FROM CinemaRoom "
	    			+ "WHERE ID = " + id);
	    
	    return "Successful";
	}
	
	public ArrayList<String[]> searchRoom(String state) throws SQLException, Exception{
            ArrayList<String[]> roomArr = new ArrayList<>();
		
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();

	    ResultSet rs = stmt.executeQuery("SELECT * FROM CinemaRoom "
	    				   + "WHERE State = '" + state + "'");
	    
	    while(rs.next()) 
		    roomArr.add(new String[] {rs.getString("ID"), rs.getString("Name"), rs.getString("Capacity"), rs.getString("State")});
	    
	    return roomArr;
	}
	
	public String getStateCheckData(String id) throws SQLException, Exception{
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    ResultSet rs = stmt.executeQuery("SELECT State FROM CinemaRoom "
		    			   + "WHERE ID = " + id);
	    rs.next();
	    
	    return rs.getString("State");
	}
	
	public ResultSet getDuplicateNameCheckData(CinemaRoom cr) throws SQLException, Exception{
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    ResultSet rs = stmt.executeQuery("SELECT ID FROM CinemaRoom "
		    			   + "WHERE Name = '" + cr.name + "' ");
	    
	    return rs;
	}
	
	public ResultSet getRoomIdCheckData(String input) throws SQLException, Exception{
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    ResultSet rs = stmt.executeQuery("SELECT ID FROM CinemaRoom"
	    							   + " WHERE ID = " + input);
	    
	    return rs;
	}
	
	public ResultSet getDuplicateRoomCheckData(CinemaRoom cr) throws SQLException, Exception{
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    ResultSet rs = stmt.executeQuery("SELECT ID FROM CinemaRoom "
	    				   + "WHERE ID = " + cr.id 
		    			   + " AND Name = '" + cr.name + "'"
		    			   + " AND Capacity = " + cr.capacity
		    			   + " AND State = '" + cr.state + "'");
	    
	    return rs;
	}
	
	public ArrayList<String[]> getRoomList() throws SQLException, Exception{
            ArrayList<String[]> roomArr = new ArrayList<>();
		
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();

	    ResultSet rs = stmt.executeQuery("SELECT * FROM CinemaRoom");
	    
	    while(rs.next()) 
		    roomArr.add(new String[] {rs.getString("ID"), rs.getString("Name"), rs.getString("Capacity"),rs.getString("State")});
	    
	    return roomArr;
	}
	
	@Override
	public String toString() {
		return String.format("ID: %s%n"
						   + "Name: %s%n"
						   + "Capacity: %d%n"
						   + "State: %s%n", this.id, this.name, this.capacity, this.state);
	}
}