package Controller;

import Entity.booking;


public class ControllerDeleteBooking {
    private static final booking bookingEntity = new booking();
    
    public ControllerDeleteBooking(){
        
    }
    
    public boolean removeBooking(int bookingID) {
        booking b = new booking();
        boolean x = b.deleteBooking(bookingID);
        
        // pass to entity class
        return x;
    }
    
}
