package Boundary;

import Controller.createfoodcontroller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class createfoodboundary extends JFrame implements ActionListener{
    private JTextField nameField;
    private JTextField descriptionField;
    private JTextField priceField;
    private createfoodcontroller c1;

    public createfoodboundary() {
        c1 = new createfoodcontroller();
        
        setTitle("Food and Beverage Management");
        setSize(300, 200);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Description:"));
        descriptionField = new JTextField();
        inputPanel.add(descriptionField);
        inputPanel.add(new JLabel("Price:"));
        priceField = new JTextField();
        inputPanel.add(priceField);
        
        JButton createButton = new JButton("Create");
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String description = descriptionField.getText();
                double price = Double.parseDouble(priceField.getText());
                
                try {
                    boolean success = c1.createfoodItem(name, description, price);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Food item created successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to create food item.");
                    }
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
                }
                
            }
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(createButton);
        
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new createfoodboundary();
            }
        });
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
