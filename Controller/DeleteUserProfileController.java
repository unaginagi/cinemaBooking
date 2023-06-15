package Controller;
import Entity.userProfile;
import java.sql.SQLException;

public class DeleteUserProfileController {
    public static boolean deleteUserProfileController(String description) throws SQLException, Exception {
        boolean isDelete = false;
        isDelete = userProfile.deleteUserProfile(description);
        return isDelete;
    }
}
