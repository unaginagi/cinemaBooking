package Controller;

import Entity.ticketType;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ControllerCreateTicketType {
    
    private static ticketType entity = new ticketType();
    
    public ControllerCreateTicketType(){
        
    }
    
    public boolean createTicketType(String typeName, double price) {
        
        // pass to entity class
        return entity.addTicketType(typeName, price);
    }
}
