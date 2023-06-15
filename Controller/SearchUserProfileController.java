
package Controller;

import Entity.userProfile;
import java.sql.SQLException;
import java.util.List;

public class SearchUserProfileController {
    public static List<userProfile> SearchUserProfileController(String searchDescription) throws SQLException, ClassNotFoundException {
        List<userProfile> profiles = userProfile.searchUserProfile(searchDescription);
        return profiles;
    }
}
