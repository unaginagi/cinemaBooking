package Controller;

import Entity.userProfile;
import java.sql.SQLException;

public class UpdateUserProfileController {
    public static boolean updateUserProfileController(String profileName, String newDescription) throws SQLException, ClassNotFoundException{
        return userProfile.updateUserProfileDescription(profileName, newDescription);
    }
}
