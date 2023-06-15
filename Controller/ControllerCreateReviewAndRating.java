
package Controller;

import Entity.ReviewAndRating;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControllerCreateReviewAndRating {
    
    private static final ReviewAndRating entity = new ReviewAndRating();
    
    public ControllerCreateReviewAndRating(){
    
    }
    
    public boolean createReviewAndRating(String Review,int Rating,int UID){
        // pass to entity class
        return entity.createReviewAndRating(Review, Rating, UID);
    }
}
