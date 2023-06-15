package Controller;

import Entity.ticketType;
import java.util.List;

public class ControllerSearchTicketType {
    
    private static ticketType entity = new ticketType();
    
    public ControllerSearchTicketType(){
        
    }
    
    public List<ticketType> searchTicketType(){
        return entity.getTicketTypesInRange(0,0);
    }
    
    public List<ticketType> searchTicketType(double minPrice, double maxPrice){
        return entity.getTicketTypesInRange(minPrice, maxPrice);
    }
}
