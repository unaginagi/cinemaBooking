package Controller;

import Entity.userAccount;
import java.sql.Date;  
import java.sql.SQLException;

public class searchUserAccountControl
{
    public searchUserAccountControl ()  
    {
        
    }

    public userAccount[] getUserAccount (Integer UID, String name, Date DOB, String user, String password,
                                        String phoneNo, String email, String address, int profileID)throws SQLException, ClassNotFoundException
    {
        userAccount ua1 = new userAccount ();
        userAccount [] output = ua1.getUserAccount (UID, name, DOB, user, password,
                                        phoneNo, email, address, profileID);
        return output;
        
    }
}