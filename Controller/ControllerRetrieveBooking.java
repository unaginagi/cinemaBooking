/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.booking;

public class ControllerRetrieveBooking {
    private static booking bookingEntity = new booking();
    
    public ControllerRetrieveBooking(){
        
    }
    //int bookingID, int roomID, String sessionTiming, int UID, int ticketID, int quantity, double price, String bookDate
    public booking retrieveBooking(int bookingID) {       
        // pass to entity class
        return bookingEntity.retrieveBookingRecord(bookingID);
    }
}
