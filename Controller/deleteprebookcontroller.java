
package Controller;

import Entity.foodsales;
import java.sql.SQLException;

public class deleteprebookcontroller {
	
    public deleteprebookcontroller() {}

    public boolean deleteprebookfood (int fsalesID) throws SQLException, ClassNotFoundException
    {
        foodsales f1 = new foodsales ();
        boolean output = f1.deleteprebookfood(fsalesID);
        return output;
    }
}