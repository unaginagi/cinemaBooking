package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controller.ControllerUpdateTicketType;

class BoundaryUpdateTicketType extends JFrame implements ActionListener {
    private JTextField oldtypeNameField;
    private JTextField newtypeNameField;
    private JTextField priceField;
    private JButton addButton;
    
    static ControllerUpdateTicketType controllerUpdate = new ControllerUpdateTicketType();

    public BoundaryUpdateTicketType() {
        setTitle("Update Ticket Type");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JLabel oldtypeNameLabel = new JLabel("Enter Type Name:");
        panel.add(oldtypeNameLabel);

        oldtypeNameField = new JTextField();
        panel.add(oldtypeNameField);
        
        JLabel newtypeNameLabel = new JLabel("Enter new Type Name:");
        panel.add(newtypeNameLabel);

        newtypeNameField = new JTextField();
        panel.add(newtypeNameField);

        JLabel priceLabel = new JLabel("Enter New Price:");
        panel.add(priceLabel);

        priceField = new JTextField();
        panel.add(priceField);
        

        addButton = new JButton("Update");
        addButton.addActionListener((ActionEvent e) -> {
            System.out.println("Updating Ticket Type...");
            
            // Get the entered values
            String oldName = oldtypeNameField.getText();
            String newName = newtypeNameField.getText();
            double newPrice = Double.parseDouble(priceField.getText());
            
            boolean result = controllerUpdate.updateTicketType(oldName, newName, newPrice);
            displayMsg(result);
            // Close the dialog
            dispose();
        });
        panel.add(addButton);

        getContentPane().add(panel);
    }

    
    private void displayMsg(boolean result){
        if (result) {
                JOptionPane.showMessageDialog(this, "Ticket Type updated successfully");
                System.out.println("Success!");
            } else {
                JOptionPane.showMessageDialog(this, "Ticket Type updated failed");
                System.out.println("Failed.");
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}