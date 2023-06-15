package Boundary;

import Controller.searchfoodcontroller;
import Controller.updateprebookcontroller;
import Entity.foodItem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class updateprebookboundary extends JFrame {
    private JComboBox<String> foodComboBox;
    private JTextField quantityField;
    private JTextField priceField;
    private updateprebookcontroller c1;

    static searchfoodcontroller searchFoodCtrl = new searchfoodcontroller();
    
    public updateprebookboundary(int fsID) {
        c1 = new updateprebookcontroller();

        setTitle("Update Prebooked Food");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        foodComboBox = new JComboBox<>();
        populateFood();
        inputPanel.add(foodComboBox);
        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField();
        inputPanel.add(quantityField);
        
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int fsalesID = fsID;
                    String selectedFood = (String) foodComboBox.getSelectedItem();
                    int foodID = getFoodID(selectedFood);
                    int quantity = Integer.parseInt(quantityField.getText());
                    double foodPrice = getFoodPrice(selectedFood);
                    double price = foodPrice*quantity;
                    
                    try {
                        boolean success = c1.updateprebookfood(foodID, quantity, price, fsalesID);
                        if (success) {
                            JOptionPane.showMessageDialog(null, "Prebooked food updated successfully.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Failed to update prebooked food.");
                        }
                    } catch (SQLException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
                    }
                } catch (Exception ex) {
                    Logger.getLogger(updateprebookboundary.class.getName()).log(Level.SEVERE,null, ex);
                }
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(updateButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void populateFood(){
           
        java.util.List<foodItem> foodlist = searchFoodCtrl.searchfoodItem("");
          
        for (foodItem f : foodlist) {
            foodComboBox.addItem(f.getName());
        }
    }
    
    private int getFoodID(String m) throws Exception{
        java.util.List<foodItem> foodlist = searchFoodCtrl.searchfoodItem("");
        
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
        java.util.List<foodItem> foodlist = searchFoodCtrl.searchfoodItem("");
        
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
