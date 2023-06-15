package Controller;

import Entity.booking;

public class ControllerCreateBooking {
    private static booking bookingEntity = new booking();
    
    public ControllerCreateBooking(){
        
    }
    
    public boolean createBooking(int roomID, String sessionTiming,int UID, int ticketID, int quantity, double price) {     
        // pass to entity class
        return bookingEntity.createBoookingRecord(roomID,sessionTiming,UID,ticketID,quantity,price);
    }
}

