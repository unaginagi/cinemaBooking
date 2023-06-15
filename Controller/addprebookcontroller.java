package Controller;

import Entity.foodItem;
import Entity.foodsales;
import java.sql.SQLException;

public class addprebookcontroller {
    public addprebookcontroller() {
            
    }

    public boolean addprebookfood(int bookingID, int foodID, int quantity, double price) throws SQLException, ClassNotFoundException {
        // Retrieve booking details
        //Booking booking = Booking.retrieveBooking(bookingID);
        
        //if (booking == null) {
       //     System.out.println("Booking not found.");
        //    return false;
        //}
        
        // Retrieve food item details
        foodItem f = new foodItem();
        
        if (f.getFood(foodID) == null) {
            System.out.println("Food item not found.");
            return false;
        }
        
        // Calculate total price
        double totalPrice = price * quantity;
        
        // Create food sale record
        foodsales fs = new foodsales();
   
        boolean output = fs.addprebookfood(bookingID, foodID, quantity, price);
        if (output == true) {
            System.out.println("Food pre-booking successful. \n");
            System.out.println("Total amount:" + totalPrice);
        } else {
            System.out.println("Failed to pre-book food.");
        }
        
        return output;
    }
}

