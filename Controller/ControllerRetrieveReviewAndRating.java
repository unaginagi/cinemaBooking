
package Controller;

import Entity.ReviewAndRating;
import java.util.List;
        
public class ControllerRetrieveReviewAndRating {
    
    private static ReviewAndRating entity = new ReviewAndRating();
    
    public ControllerRetrieveReviewAndRating(){
        
    }
    
    public List<ReviewAndRating> retrieveReviewAndRating(){
        return entity.checkReviewAndRating();
    }
}
