package Controller;

import Entity.userAccount;
import java.sql.Date;  
import java.sql.SQLException;

public class createCustomerAccountControl
{
    public createCustomerAccountControl ()  
    {
        
    }

    public boolean createUserAccount (String name, Date DOB, String user, String password,
                                        String phoneNo, String email, String address, int profileID) throws SQLException, ClassNotFoundException
    {
        
        userAccount ua1 = new userAccount ();
        boolean output = ua1.addUserAccount (name, DOB, user, password, phoneNo, email, address, profileID);
        return output;
    }
}