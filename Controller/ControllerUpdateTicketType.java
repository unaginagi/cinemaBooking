package Controller;

import Entity.ticketType;

public class ControllerUpdateTicketType {
    
    private static ticketType entity = new ticketType();
    
    public ControllerUpdateTicketType(){
        
    }
    
    public boolean updateTicketType(String oldName, String newName, double newPrice){
        return entity.sendTicketDetails(oldName, newName, newPrice);
    }
}
