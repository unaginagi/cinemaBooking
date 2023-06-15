package Controller;

import Entity.foodItem;
import java.sql.SQLException;

public class retrievefoodcontroller {
    public retrievefoodcontroller() {
            
    }

    public foodItem retrievefoodItem (int id)
    {
        foodItem f1 = new foodItem ();
        foodItem output = f1.getFood (id);
        System.out.println(output.getName());
        return output;
    }
}


