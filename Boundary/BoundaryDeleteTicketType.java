package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controller.ControllerDeleteTicketType;

class BoundaryDeleteTicketType extends JFrame implements ActionListener {
    private JTextField typeNameField;
    private JButton addButton;
    
    static ControllerDeleteTicketType controllerDelete = new ControllerDeleteTicketType();

    private String typeName;

    public BoundaryDeleteTicketType() {
        setTitle("Delete Ticket Type");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));

        JLabel typeNameLabel = new JLabel("Type Name:");
        panel.add(typeNameLabel);

        typeNameField = new JTextField();
        panel.add(typeNameField);

        addButton = new JButton("Remove");
        addButton.addActionListener((ActionEvent e) -> {
            System.out.println("Removing Ticket Type...");
            
            // Get the entered values
            typeName = typeNameField.getText();
            
            boolean result = controllerDelete.deleteTicketType(typeName);
            displayMsg(result);
            
            // Close the dialog
            dispose();
        });
        panel.add(addButton);

        getContentPane().add(panel);
    }

    private void displayMsg(boolean result){
        if (result) {
                JOptionPane.showMessageDialog(this, "Ticket Type removed successfully");
                System.out.println("Success!");
            } else {
                JOptionPane.showMessageDialog(this, "Ticket Type removed unsuccessfully");
                System.out.println("Failed.");
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}