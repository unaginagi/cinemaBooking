package Controller;

import Entity.ticketType;

public class ControllerDeleteTicketType {
    
    private static ticketType entity = new ticketType();
    
    public ControllerDeleteTicketType(){
        
    }
    
    public boolean deleteTicketType(String typeName){
        return entity.deleteTicketType(typeName);
    }
}
