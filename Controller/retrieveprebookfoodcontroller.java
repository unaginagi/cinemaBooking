package Controller;

import Entity.foodsales;
import java.sql.SQLException;

public class retrieveprebookfoodcontroller {
    public retrieveprebookfoodcontroller() {
            
    }

    public foodsales getprebookfood(int fsalesID) throws SQLException, ClassNotFoundException {
        foodsales f1 = new foodsales();
        foodsales output = f1.getprebookfood(fsalesID);
        return output;
    }
}
