package Controller;
import Entity.userAccount;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginController{
        
    public static int[] login(String username, String password) throws SQLException, Exception{
        return userAccount.validateLogin(username,password);
    }
}