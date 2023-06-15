package Entity;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class booking {
    private int bookingID;
    private int roomID;
    private String sessionTiming;
    private int UID;
    private int ticketID; 
    private int quantity;
    private double price;
    private String bookDate;
    private String seating;

    public booking(){
        
    }
    
    public booking(int bookingID, int roomID, String sessionTiming, int UID, int ticketID, int quantity, double price, String bookDate, String seating) {
        this.bookingID = bookingID;
        this.roomID = roomID;
        this.sessionTiming = sessionTiming;
        this.UID = UID;
        this.ticketID = ticketID;
        this.quantity = quantity;
        this.price = price;
        this.bookDate = bookDate;
        this.seating = seating;
    }
    
    public booking(booking b) {
        this.bookingID = b.getBookingID();
        this.roomID = b.getRoomID();
        this.sessionTiming = b.getSessionTiming();
        this.UID = b.getUID();
        this.ticketID = b.getTicketID();
        this.quantity = b.getQuantity();
        this.price = b.getPrice();
        this.bookDate = b.getBookDate();
        this.seating = b.getSeating();
    }   
    
    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public String getSessionTiming() {
        return sessionTiming;
    }

    public void setSessionTiming(String sessionTiming) {
        this.sessionTiming = sessionTiming;
    }

    public int getUID() {
        return UID;
    }

    public void setUID(int UID) {
        this.UID = UID;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int ticketID) {
        this.ticketID = ticketID;
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

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }
    
    public String getSeating() {
        return seating;
    }

    public void setSeating(String seating) {
        this.seating = seating;
    }

    final String url = "jdbc:mysql://localhost:3306/cinemabooking";
    final String username = "root";
    final String dbpassword = "password";
    
    public boolean createBoookingRecord(int roomID, String sessionTiming, int UID, int ticketID, int quantity, double price){
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
            
            System.out.println("Connected, attempting to insert booking...");
            System.out.println(sessionTiming);
            System.out.println(roomID);
            System.out.println(ticketID);
                
            //Check if cinemaRoom has capacity to fit
            /*-------------------------------------------------------*/
            
            PreparedStatement pstmt1 = conn.prepareStatement("Select seating from booking where roomID = ? and SessionTiming = ? ORDER BY seating DESC");
            pstmt1.setInt(1, roomID);
            
            Timestamp sessionTimestamp = Timestamp.valueOf(sessionTiming);
            pstmt1.setTimestamp(2, sessionTimestamp);
            
            ResultSet rs = pstmt1.executeQuery();
            
            
            // Execute the SQL statement
            int seating = 0;
            String bookseating;
            String [] bseating = {};
            while(rs.next() || rs.isFirst() == false)
            {
                System.out.println("in while loop");
                bookseating = null;
                if (rs.isFirst() != false){
                    bookseating = rs.getString("seating");
                }
                if (bookseating != null && bookseating.contains(","))
                {
                    bseating = bookseating.split(",");
                }
                if (bseating.length >= 2)
                {
                    seating = Integer.parseInt(bseating[bseating.length-1]);
                    seating += 1;
                }
                else if (bookseating != null && Integer.parseInt(bookseating) == seating + 1)
                {
                    seating += 1;                 
                }
                else
                {
                    seating += 1;
                    //break;
                }
                System.out.println("seating capacity: " + seating);
                PreparedStatement pstmt2 = conn.prepareStatement("Select Capacity from cinemaroom where ID = ?");
                pstmt2.setInt(1, roomID);
                
                ResultSet rs1 = pstmt2.executeQuery();
                if (rs1.next())
                {
                    int capacity = rs1.getInt("Capacity");
                    if (seating > capacity)
                    {
                        return false;
                    }
                }
                
                // Prepare the SQL statement
                String sql = "INSERT INTO booking (roomID, SessionTiming, UID, ticketID, quantity, price, seating) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, roomID);
                
                pstmt.setTimestamp(2, sessionTimestamp);
                
                pstmt.setInt(3, UID);
                pstmt.setInt(4, ticketID);
                pstmt.setInt(5, quantity);
                pstmt.setDouble(6,price);
                
                String seat = String.valueOf(seating);
                if (quantity > 1)
                {
                    for (int i = 1; i < quantity; i++)
                    {
                        seating += 1;
                        seat += "," +  String.valueOf(seating);
                    }
                }
                pstmt.setString (7, seat);
                // Execute the SQL statement
                
                int rowsInserted = pstmt.executeUpdate();
                
                // Close the resources
                pstmt.close();
                conn.close();
                
                return rowsInserted > 0;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
}
    
    public booking retrieveBookingRecord(int bookID){
        try {
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
            
            System.out.println("Connected, attempting to retrieve booking...");
            
            // Prepare the SQL statement
            String sql = "SELECT * FROM booking WHERE bookingID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            //pstmt.setInt(1, b.getBookingID());
            pstmt.setInt(1, bookID);
            
            // Execute the SQL statement
            ResultSet rs = pstmt.executeQuery();

            // Extract the ticket type details from the result set
            if (rs.next()) {
                int bookingID = rs.getInt("bookingID");
                int roomID = rs.getInt("roomID");
                String sessionTiming = rs.getString("sessionTiming");
                int UID = rs.getInt("UID");
                int ticketID = rs.getInt("ticketID");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                String bookDate = rs.getString("book_date");
                String seatingNo = rs.getString("seating");

                booking b = new booking(bookingID,roomID,sessionTiming,UID,ticketID,quantity,price,bookDate,seatingNo);
                
                // Close the resources
                rs.close();
                pstmt.close();
                conn.close();

                return b;
            } else {
                // Close the resources
                rs.close();
                pstmt.close();
                conn.close();

                return null; // No ticket type with the given ID was found
            }
        } catch (SQLException e) {
            System.out.println("SELECT SQL error!");
            e.printStackTrace();
            return null;
        }
    }
    public ArrayList<String[]> getBookingList(int ID){
        try {
            ArrayList<String[]> bookingArr = new ArrayList<>();
            
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
            Statement stmt = conn.createStatement();
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM booking where UID =" + ID);
            
            while(rs.next())
                bookingArr.add(new String[] {rs.getString("bookingID"), rs.getString("roomID"), rs.getString("sessionTiming"),
                    rs.getString("UID"), rs.getString("ticketID"), rs.getString("quantity"), rs.getString("price"), rs.getString("book_date"), rs.getString("seating")});
            
            return bookingArr;
        } catch (SQLException ex) {
            Logger.getLogger(booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean deleteBooking(int bookingID)
    {
        try {
            Connection conn = DriverManager.getConnection(url,username,dbpassword);
            Statement stmt = conn.createStatement();
            
            String sql = "Delete from booking where bookingID = "+ bookingID;
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            int rowsDeleted = pstmt.executeUpdate();
            
            // Close the resources
            pstmt.close();
            conn.close();
            
            return rowsDeleted >0;
            
        } catch (SQLException ex) {
            return false;
        }
        
    }
}

