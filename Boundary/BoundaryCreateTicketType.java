package Boundary;

import Controller.ControllerCreateTicketType;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BoundaryCreateTicketType extends JFrame implements ActionListener{
    private JTextField typeNameField;
    private JTextField priceField;
    private JButton addButton;
    
    static ControllerCreateTicketType controllerCreate = new ControllerCreateTicketType();

    private String typeName;
    private double price;
    private int ageLimit;

    public BoundaryCreateTicketType() {
        setTitle("Create Ticket Type");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        
        JLabel typeNameLabel = new JLabel("Type Name:");
        panel.add(typeNameLabel);
        
        //get type name input
        typeNameField = new JTextField();
        panel.add(typeNameField);

        
        JLabel priceLabel = new JLabel("Price:");
        panel.add(priceLabel);
        
        //get price input
        priceField = new JTextField();
        panel.add(priceField);
        

        addButton = new JButton("Add");
        addButton.addActionListener((ActionEvent e) -> {
            System.out.println("Creating Ticket Type...");
            
            // Get the entered values
            typeName = typeNameField.getText();
            price = Double.parseDouble(priceField.getText());
            
            //passing to controller
            boolean result = controllerCreate.createTicketType(typeName, price);
            displayMsg(result);
            // Close the dialog
            dispose();
        });
        panel.add(addButton);

        getContentPane().add(panel);
    }

    
    private void displayMsg(boolean result){
        if (result) {
                JOptionPane.showMessageDialog(this, "Ticket Type added successfully");
                System.out.println("Success!");
            } else {
                JOptionPane.showMessageDialog(this, "Duplicate record found");
                System.out.println("Failed.");
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}