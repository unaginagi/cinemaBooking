package Boundary;

import Controller.addprebookcontroller;
import Controller.searchfoodcontroller;
import Entity.foodItem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class addprebookboundary extends JFrame {
    private JComboBox<String> foodComboBox;
    private JTextField quantityField;
    private addprebookcontroller c1;

    static searchfoodcontroller searchFoodCtrl = new searchfoodcontroller();
    public addprebookboundary(int bookID) {
        c1 = new addprebookcontroller();

        setTitle("Pre-Booking Food and Beverage");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(6, 2));
        foodComboBox = new JComboBox<>();
        populateFood();
        inputPanel.add(foodComboBox);
        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int bookingID = bookID;
                    String selectedFood = (String) foodComboBox.getSelectedItem();
                    int foodID = getFoodID(selectedFood);
                    int quantity = Integer.parseInt(quantityField.getText());
                    double foodPrice = getFoodPrice(selectedFood);
                    double price = foodPrice*quantity;
                    
                    try {
                        boolean success = c1.addprebookfood(bookingID, foodID, quantity, price);
                        if (success) {
                            JOptionPane.showMessageDialog(null, "Prebook food added successfully.");
                            dispose();
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to add prebook food.");
                            dispose();
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
                        dispose();
                    }
                } catch (Exception ex) {
                    java.util.logging.Logger.getLogger(addprebookboundary.class.getName()).log(java.util.logging.Level.SEVERE,null, ex);
                    dispose();
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        
    }
    
    private void populateFood(){
           
        List<foodItem> foodlist = searchFoodCtrl.searchfoodItem("");
          
        for (foodItem f : foodlist) {
            foodComboBox.addItem(f.getName());
        }
    }
    
    private int getFoodID(String m) throws Exception{
        List<foodItem> foodlist = searchFoodCtrl.searchfoodItem("");
        
        for (foodItem f : foodlist) {
            String foodName = f.getName();
            int foodID = f.getId() ;
            if (foodName.equals(m)){
                return foodID;
            }
        }
        return 0;
     }
    
    private double getFoodPrice(String m) throws Exception{
        List<foodItem> foodlist = searchFoodCtrl.searchfoodItem("");
        
        for (foodItem f : foodlist) {
            String foodName = f.getName();
            double foodPrice = f.getPrice() ;
            if (foodName.equals(m)){
                return foodPrice;
            }
        }
        return 0;
     }
}
