package Controller;

import Entity.userProfile;
import java.sql.SQLException;

public class RetrieveUserProfileController {
    public static userProfile retrieveUserProfileController(int profileID) throws SQLException, Exception{
        
        userProfile retrievedUserProfile = userProfile.getUserProfile(profileID);
        return retrievedUserProfile;
    }  
    
}
