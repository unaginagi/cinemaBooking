package Boundary;

import Controller.updatefoodcontroller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class updatefoodboundary extends JFrame {
    private JTextField idField;
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField priceField;
    private updatefoodcontroller c1;

    public updatefoodboundary() {
        c1 = new updatefoodcontroller();
        
        setTitle("Food and Beverage Management");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);
        
        JButton updateButton = new JButton("Update based on ID");
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                String description = descriptionField.getText();
                double price = Double.parseDouble(priceField.getText());
                
                try {
                    boolean success = c1.updatefoodItem(id, name, description, price);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Food item updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update food item.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
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
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new updatefoodboundary();
            }
        });
    }
}
