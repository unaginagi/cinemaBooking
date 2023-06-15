package Controller;

import Entity.ticketType;

public class ControllerRetrieveTicketType {
    
    private static ticketType entity = new ticketType();
    
    public ControllerRetrieveTicketType(){
        
    }
    
    public ticketType retrieveTicketType(String typeName){
        //pass to entity object
        return entity.checkTicketType(typeName);
    }
    
}
