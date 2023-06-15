/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Boundary;

import Controller.retrieveprebookfoodcontroller;
import Controller.searchfoodcontroller;
import Entity.foodItem;
import Entity.foodsales;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class retrieveprebookboundary extends JFrame {
    private retrieveprebookfoodcontroller c1;
    private searchfoodcontroller searchFoodCtrl = new searchfoodcontroller();
    public retrieveprebookboundary(int fsID1) {
        c1 = new retrieveprebookfoodcontroller();
            try {
                int id = fsID1;
                foodsales prebookedFood = c1.getprebookfood(id);
                String foodName = getFoodName(prebookedFood.getFoodID());
                if (prebookedFood != null) {
                    // Display the prebooked food details
                    JOptionPane.showMessageDialog(null,
                            "Food Sales ID: " + prebookedFood.getFsalesID() + "\n" +
                            "Booking ID: " + prebookedFood.getBookingID() + "\n" +
                            "Food ID: " + prebookedFood.getFoodID() + "\n" +
                            "Food Name: " + foodName + "\n" +
                            "Quantity: " + prebookedFood.getQuantity() + "\n" +
                            "Price: " + prebookedFood.getPrice() + "\n");
                } else {
                    JOptionPane.showMessageDialog(null, "Prebooked food not found.");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Invalid Food Sales ID.");
            } catch (SQLException | ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
            }
        }
    
    private String getFoodName(int id){
        java.util.List<foodItem> foodlist = searchFoodCtrl.searchfoodItem("");
        
        for (foodItem f : foodlist) {
            String foodName = f.getName();
            int foodID = f.getId();
            if(foodID == id){
                return foodName;
            }
        }
        return "";
    }
}


