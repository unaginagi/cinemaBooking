package Controller;

import Entity.userAccount;
import java.sql.SQLException;

public class suspendUserAccountControl
{
    public suspendUserAccountControl ()  
    {
        
    }

    public boolean LockUserAccount (int UID)throws SQLException, ClassNotFoundException
    {
        userAccount ua1 = new userAccount ();
        boolean output = ua1.suspendUserAccount (UID);
        return output;
    }
}