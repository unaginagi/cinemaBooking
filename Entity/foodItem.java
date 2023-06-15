package Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class foodItem{
	private int id;
	private String name;
	private String description;
	private double price;
	
	public foodItem() {}

	public foodItem(int id, String name, String description, double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
	}

	public foodItem(foodItem f) {
		this(f.getId(), f.getName(), f.getDescription(), f.getPrice());
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

 /*      
	public boolean addFood(String id,String name, String description, double price)throws SQLException, ClassNotFoundException {
		
            try{
                String URL = "jdbc:mysql://localhost/";
                Class.forName ("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection (URL + "csit314",  "", "");

                PreparedStatement stmt; 
                stmt = conn.prepareStatement("INSERT INTO food_items(id, name, description, price)"
						+ "VALUES ('" + id
                                                + "', '" + name
						+ "', '" + description 
						+ "', '" + price + "')");
		ResultSet rs = stmt.executeQuery();
		stmt.close();
	        conn.close();
		return true;
            }
            catch (SQLException e )
            {
                return false;
            }
	}
 */	
        final String url = "jdbc:mysql://localhost:3306/cinemabooking";
        final String username = "root";
        final String dbpassword = "password";
    
        public boolean addFood(String name, String description, double price){
            try {
                Connection conn;
                conn = DriverManager.getConnection(url,username,dbpassword);
                System.out.println("name: " + name);
                System.out.println("description: " + description);
                System.out.println("price: " + price);
                PreparedStatement stmt;
                
                String sql = "INSERT INTO food_items (name, description, price) VALUES (?, ?, ?)";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, name);
                stmt.setString(2, description);
                stmt.setDouble(3, price);
                
                int rowsAffected = stmt.executeUpdate();
                stmt.close();
                conn.close();

                return rowsAffected > 0;
            } catch (SQLException e) {
                return false;
            }
        }

	public foodItem getFood(int id){
            try{
                Connection conn = DriverManager.getConnection(url,username,dbpassword);

                PreparedStatement stmt;
                stmt = conn.prepareStatement("SELECT * FROM food_items WHERE ID = ?");
                stmt.setInt(1,id);

                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    // Retrieve the data from the ResultSet and create a new foodItem object
                    int fid = rs.getInt("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    return new foodItem(fid, name, description,price);
                }else{
                    return null;
                }
	    }
            catch (SQLException e )
            {
                return null;
            }
	}
	
	public boolean updateFood(int id, String name, String description, double price){
            try{
                Connection conn = DriverManager.getConnection(url,username,dbpassword);
                PreparedStatement stmt; 
                stmt = conn.prepareStatement("UPDATE food_items SET name = ?, description = ?, price = ? WHERE id = ?");
                stmt.setString(1, name);
                stmt.setString(2, description);
                stmt.setDouble(3, price);
                stmt.setInt(4,id);
                
                int rowsAffected = stmt.executeUpdate();
                stmt.close();
                conn.close();

                return rowsAffected > 0; // Return true if at least one row was deleted
            }
            catch (SQLException e )
            {
                return false;
            }
	}
	
	public boolean deleteFood(int id){
            try{
                Connection conn = DriverManager.getConnection(url,username,dbpassword);
            
                PreparedStatement stmt; 
                stmt = conn.prepareStatement("DELETE FROM food_items WHERE id = ?");
                stmt.setInt(1,id);
                
                int rowsAffected = stmt.executeUpdate();
                stmt.close();
                conn.close();

                return rowsAffected > 0; // Return true if at least one row was deleted
            }
		catch (SQLException e )
            {
                return false;
            }
	}
/*
	public foodItem searchFood(String name) throws SQLException, ClassNotFoundException{
		try{
			String URL = "jdbc:mysql://localhost/";
                        Class.forName ("com.mysql.jdbc.Driver");
                        Connection conn = DriverManager.getConnection (URL + "csit314",  "", "");
            
			PreparedStatement stmt; 
			stmt = conn.prepareStatement("SELECT * FROM food_items "
							  + "WHERE name = '" + name + "'");
                        ResultSet rs = stmt.executeQuery();
			rs.next();

			return new foodItem(rs.getString("id"), rs.getString("name"), rs.getString("description"), rs.getDouble("price"));
		}
		catch (SQLException e )
        {
            return null;
        }
	}
	
*/
        public List<foodItem> searchfood(String name){
            try {
                Connection conn = DriverManager.getConnection(url,username,dbpassword);
                
                List<foodItem> searched = new ArrayList<>();
                PreparedStatement stmt = null;
                
                if (name == ""){
                    stmt = conn.prepareStatement("SELECT * FROM food_items");
                }else{
                    stmt = conn.prepareStatement("SELECT * FROM food_items WHERE name = ?");
                    stmt.setString(1, name);
                }

                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int id1 = rs.getInt("id");
                    String name1 = rs.getString("name");
                    String description1 = rs.getString("description");
                    double price1 = rs.getDouble("price");

                    foodItem output = new foodItem(id1, name1, description1, price1);
                    searched.add(output);
                }

                return searched;
            } catch (SQLException e) {
                return null;
            }
        }


        @Override
        
        public String toString() {
            return String.format("ID: %d%n"
                                 + "Name: %s%n"
                                 + "Description: %s%n"
                                 + "Price: %s%n", this.id, this.name, this.description, this.price);
        }
	
/*	public static void main (String args [])throws SQLException, ClassNotFoundException
    {
		try{
			foodItem f = new foodItem();
			//String URL = "jdbc:mysql://localhost/";
			//Class.forName ("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection (URL + "csit314",  "root", "123");
			System.out.println("hello");
			f.searchFood("Hot Dog");
		}
		catch (SQLException e )
        {
            return;
        }
    }*/

   

    
	
}	

