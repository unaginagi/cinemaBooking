package Controller;
  
import Entity.foodItem;
import java.sql.SQLException;

public class updatefoodcontroller {
	
	public updatefoodcontroller() {}

	public boolean updatefoodItem (int id, String name, String description, double price) throws SQLException, ClassNotFoundException
    {
        
        foodItem f1 = new foodItem ();
        boolean output = f1.updateFood (id, name, description, price);
        return output;
    }
	

}

