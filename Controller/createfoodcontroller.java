package Controller;

import Entity.foodItem;
import java.sql.SQLException;

public class createfoodcontroller {
    public createfoodcontroller() {
            
    }

    public boolean createfoodItem(String name, String description, double price) throws SQLException, ClassNotFoundException {
        foodItem f1 = new foodItem();
        boolean output = f1.addFood(name, description, price);
        return output;
    }
}

