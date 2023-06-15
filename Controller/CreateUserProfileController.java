package Controller;

import Entity.userProfile;
import java.sql.SQLException;

public class CreateUserProfileController {
    
    public static boolean addUserProfileController(String profileName) throws SQLException, Exception{
        boolean isLoginSuccessful = false;
        isLoginSuccessful = userProfile.addUserProfile(profileName);
        return isLoginSuccessful;
    }    
}
