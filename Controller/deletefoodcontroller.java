package Controller;
 
import Entity.foodItem;
import java.sql.SQLException;

public class deletefoodcontroller {
	
	public deletefoodcontroller() {}

	public boolean deletefoodItem (int id) throws SQLException, ClassNotFoundException
    {
        
        foodItem f1 = new foodItem ();
        boolean output = f1.deleteFood (id);
        return output;
    }
	
/*	
	public static void main (String args []) throws SQLException, ClassNotFoundException
    {
		String URL = "jdbc:mysql://localhost/";
		Class.forName ("com.mysql.jdbc.Driver");
		Connection conn = DriverManager.getConnection (URL + "csit314",  "root", "123");
#   }


*/
}