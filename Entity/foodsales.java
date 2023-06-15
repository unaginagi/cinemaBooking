package Entity;

import Controller.GetBookingListController;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;



public class foodsales{
    private int fsalesID;
    private int bookingID;
    private int foodID;
    private int quantity;
    private double price;
    
    static GetBookingListController getBookCtrl = new GetBookingListController();
    
    public foodsales() {
        
    }
    public foodsales(int fsalesID, int bookingID, int foodID, int quantity, double price) {
        this.fsalesID = fsalesID;
        this.bookingID = bookingID;
        this.foodID = foodID;
        this.quantity = quantity;
        this.price = price;
    }

    

    public int getFsalesID() {
        return fsalesID;
    }

    public void setFsalesID(int fsalesID) {
        this.fsalesID = fsalesID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getFoodID() {
        return foodID;
    }

    public void setFoodID(int foodID) {
        this.foodID = foodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    
    final String url = "jdbc:mysql://localhost:3306/cinemabooking";
    final String username = "root";
    final String dbpassword = "password";
    
    public boolean addprebookfood(int bookingID, int foodID, int quantity, double price) throws SQLException, ClassNotFoundException {
        try {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);

            PreparedStatement stmt;
            stmt = conn.prepareStatement("INSERT INTO food_sales (bookingID, foodID, quantity, price) VALUES (?, ?, ?, ?)");
            stmt.setInt(1, bookingID);
            stmt.setInt(2, foodID);
            stmt.setInt(3, quantity);
            stmt.setDouble(4, price);

            int rowsInserted = stmt.executeUpdate();
            stmt.close();
            conn.close();

            return rowsInserted > 0;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public foodsales getprebookfood(int fsalesID) throws SQLException, ClassNotFoundException{
        try{
            Connection conn = DriverManager.getConnection(url,username,dbpassword);

            PreparedStatement stmt;
	    //stmt = conn.prepareStatement("SELECT * FROM food_items "
							//+ "WHERE ID = " + fsalesID);
            
            String sql = "SELECT * FROM food_sales WHERE fsalesID = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, fsalesID);
            
            ResultSet rs = stmt.executeQuery();
            // Extract the food sales details from the result set
            if (rs.next()) {
                int fsales = rs.getInt("fsalesID");
                int booking = rs.getInt("bookingID");
                int food = rs.getInt("foodID");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");

                foodsales f = new foodsales(fsales, booking, food,quantity, price);

                // Close the resources
                rs.close();
                stmt.close();
                conn.close();

                return f;
            }else{
                // Close the resources
                rs.close();
                stmt.close();
                conn.close();
                
                return null;
            }

	}
        catch (SQLException e )
        {
            return null;
        }
    }

    // Update food sales
    public boolean updateprebookfood(int foodID, int quantity, double price, int fsalesID) throws SQLException, ClassNotFoundException {
        try {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);

            PreparedStatement stmt = conn.prepareStatement("UPDATE food_sales SET foodID = ?, quantity = ?, price = ? WHERE fsalesID = ?");
            stmt.setInt(1, foodID);
            stmt.setInt(2, quantity);
            stmt.setDouble(3, price);
            stmt.setInt(4, fsalesID);
            
            // Execute the SQL statement
            int rowsUpdated = stmt.executeUpdate();
            
            stmt.close();
            conn.close();

            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    // Delete food sales
    public boolean deleteprebookfood(int fsalesID) throws SQLException, ClassNotFoundException {
        try {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);

            PreparedStatement stmt = conn.prepareStatement("DELETE FROM food_sales WHERE fsalesID = ?");
            stmt.setInt(1, fsalesID);

            // Execute the SQL statement
            int rowsDeleted = stmt.executeUpdate();
            
            stmt.close();
            conn.close();

            return rowsDeleted > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    // Search food sales by booking ID
    public ArrayList<String []> searchprebookfood(int UID) throws SQLException, ClassNotFoundException {
        try {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
            
            ArrayList<String []> bookings = getBookCtrl.executeTask(UID);
            ArrayList<String []> searched = new ArrayList<>();
            //List<foodsales> searched = new ArrayList<>();
            for (String [] book : bookings)
            {
                if (book[3].equals(Integer.toString(UID))){
                    int bookingID = Integer.parseInt(book[0]);
                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM food_sales WHERE bookingID = ?");
                    stmt.setInt(1, bookingID);
                    ResultSet rs = stmt.executeQuery();
                    
                    while (rs.next()) {
                        searched.add(new String[] {
                            rs.getString("fsalesID"),
                            rs.getString("bookingID"), 
                            rs.getString("foodID"),
                            rs.getString("quantity"),
                            rs.getString("price"),
                        });
                    }
                }
            }
            return searched;
        } catch (SQLException e) {
            return null;
        }
    }
}
    

