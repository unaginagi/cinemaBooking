
package Entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;        
        
        
public class ReviewAndRating {
    private int ReviewID;
    private String Review;
    private int Rating;
    private int UID;
    private String ReviewDate;
    
    public ReviewAndRating(){}
        
    public ReviewAndRating(int ReviewID, String Review, int Rating, int UID) {
        setReviewID(ReviewID);
        setReview(Review);
        setRating(Rating);
        setUID(UID);
    }

    public ReviewAndRating(ReviewAndRating r){
        setReviewID(r.getReviewID());
        setReview(r.getReview());
        setRating(r.getRating());
        setUID(r.getUID());
    }

    public int getReviewID(){
        return ReviewID;
    }

    public String getReview(){
        return Review;
    }

    public int getRating(){
        return Rating;
    }

    public int getUID(){
        return UID;
    }        

    public String getReviewDate(){
            return ReviewDate;
    }

    private void setReviewID(int ReviewID){
        this.ReviewID = ReviewID;
    }

    private void setReview(String Review){
        this.Review = Review;
    }

    private void setRating(int Rating){
        this.Rating = Rating;
    }

    private void setUID(int UID){
        this.UID = UID;
    }

    private void setReviewDate(String ReviewDate){
        this.ReviewDate = ReviewDate;
    }        

     final String url = "jdbc:mysql://localhost:3306/cinemabooking";
     final String username = "root";
     final String dbpassword = "password";

    public boolean createReviewAndRating(String Review,int Rating,int UID){
        try{
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(url,username,dbpassword);  

            // Prepare SQL statement
            String sql = "INSERT INTO ReviewAndRating(Review,Rating,UID) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, Review);
            pstmt.setInt(2, Rating);
            pstmt.setInt(3, UID);

            // Execute SQL statement
            int rowsInserted = pstmt.executeUpdate();

            // Close the resources
            pstmt.close();
            conn.close();
            
            return rowsInserted > 0;
        }catch (SQLException e){
            System.out.println("Insert SQL error!");
            e.printStackTrace();
        }
        return false;
    }

    public List<ReviewAndRating> checkReviewAndRating(){
        List<ReviewAndRating> RAR = new ArrayList<>();
        try{
            // Establish a connection to the database
            Connection conn = DriverManager.getConnection(url, username, dbpassword);
            PreparedStatement pstmt;
            ResultSet rs;

            // Show all of the review and ratings
            String sql = "SELECT * FROM ReviewAndRating";
            pstmt = conn.prepareStatement(sql);

            // Execute the SQL statement
            rs = pstmt.executeQuery();

            // Extract the Review and Ratings from the result set
            while (rs.next()) {
                int ReviewID = rs.getInt("ReviewID");
                String Review = rs.getString("Review");
                int Rating = rs.getInt("Rating");

                ReviewAndRating rar = new ReviewAndRating(ReviewID,Review,Rating,UID);
                RAR.add(rar);
            }
            // Close the resources
            rs.close();
            pstmt.close();
            conn.close();

            return RAR;
        } catch (SQLException e){
            System.out.println("Search SQL error!");
            e.printStackTrace();
            return null;
            }
    }

}
        

    
