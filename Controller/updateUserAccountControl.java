package Controller;

import Entity.userAccount;
import java.sql.Date;  
import java.sql.SQLException;

public class updateUserAccountControl
{
    public updateUserAccountControl ()  
    {
        
    }

    public boolean updateUserAccount (int UID, String name, Date DOB, String user, String password,
                                    String phoneNo, String email, String address) throws SQLException, ClassNotFoundException
    {
        userAccount ua1 = new userAccount ();
        boolean output = ua1.updatingUserAccount (UID, name, DOB, user, password,
                                        phoneNo, email, address);

        return output;
    }

}