package Entity;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Movie{
	private String id;
	private String name;
	private String description;
	private String genre;
	private int duration;
	
	public Movie() {}
	
	public Movie(String id, String name, String description, String genre, int duration) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.genre = genre;
		this.duration = duration;
	}
	
	public Movie(String name, String description, String genre, int duration) {
		this(null, name, description, genre, duration);
	}

	public Movie(Movie m) {
		this(m.id, m.name, m.description, m.genre, m.duration);
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}
	
        final String url = "jdbc:mysql://localhost:3306/cinemabooking";
        final String username = "root";
        final String dbpassword = "password";
        
	public String addMovie(Movie m) throws SQLException, Exception {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    stmt.executeUpdate("INSERT INTO Movie (Name, Description, Genre, Duration)"
				+ "values ('" + m.name 
				+ "', '" + m.description 
				+ "', '" + m.genre
				+ "', " + m.duration + ")");
	         
	    stmt.close();
	    conn.close();
		
	    return "Successful";
	}
	
	public String[] retrieveMovie(String id) throws SQLException, Exception {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery("SELECT * FROM Movie "
									  + "WHERE ID = " + id);
		
	    rs.next();
	    
	    return new String[] {rs.getString("ID"), rs.getString("Name"), 
				 rs.getString("Description"), rs.getString("Genre"), rs.getString("Duration")};
	}

	public String updateMovie(Movie m) throws SQLException, Exception {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    stmt.executeUpdate("UPDATE Movie "
         	 	+ "SET Name = '" + m.name 
         	 	+ "', Description = '" + m.description 
         	 	+ "', Genre = '" + m.genre
         	 	+ "', Duration = " + m.duration
         	 	+ " WHERE ID = " + m.id);
	         
	    stmt.close();
	    conn.close();
	    
	    return "Successful";

	}
	
	public String deleteMovie(String id) throws SQLException, Exception {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
		
	    stmt.executeUpdate("DELETE FROM Movie"
	    			+ " WHERE ID = " + id);
	    
	    return "Successful";
	}
	
	public String[] searchMovie(String name) throws SQLException, Exception{
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    ResultSet rs = stmt.executeQuery("SELECT * FROM Movie "
	         			   + "WHERE Name = '" + name + "'");
	    
	    if(rs.next()) 
		    return new String[] {rs.getString("ID"), rs.getString("Name"), 
		    				 rs.getString("Description"), rs.getString("Genre"), rs.getString("Duration")};
	    else
	    	return new String[] {};
	    
	}
	
	public ResultSet getMovieIdCheckData(String input) throws SQLException, Exception{
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    ResultSet rs = stmt.executeQuery("SELECT ID FROM Movie"
	    							   + " WHERE ID = " + input);
	    
	    return rs;
	}
	
	public ResultSet getDuplicateMovieCheckData(Movie m) throws SQLException, Exception{
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	    
	    ResultSet rs = stmt.executeQuery("SELECT ID FROM Movie "
		    			   + "WHERE Name = '" + m.name + "' "
		    			   + "AND Description = '" + m.description + "' "
		    			   + "AND Genre = '" + m.genre + "' "
		    			   + "AND Duration = " + m.duration);
	    
	    return rs;
	}
	
	public ArrayList<String[]> getMovieList() throws SQLException, Exception{
		ArrayList<String[]> movieArr = new ArrayList<>();
		
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
		
	    Statement stmt = conn.createStatement();
	         
	    ResultSet rs = stmt.executeQuery("SELECT ID, Name FROM Movie");
	    
	    while(rs.next())
	    	movieArr.add(new String[] {rs.getString("ID"), rs.getString("Name")});
	    
	    return movieArr;
	}
	
	@Override
	public String toString() {
		return String.format("ID: %s%n"
						   + "Name: %s%n"
						   + "Description: %s%n"
						   + "Genre: %s%n"
						   + "Duration: %d%n", this.id, this.name, this.description, this.genre, this.duration);
	}
}