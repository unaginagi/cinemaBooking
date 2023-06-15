package Controller;

import Entity.userAccount;
import java.sql.SQLException;

public class retrieveUserAccountControl
{
    public retrieveUserAccountControl ()  
    {
        
    }

    public userAccount retrieveUserAccountInfo (int UID) throws SQLException, ClassNotFoundException
    {

        userAccount ua1 = new userAccount ();

        userAccount output = ua1.getUserAccount (UID);
    
        return output;
    }

}