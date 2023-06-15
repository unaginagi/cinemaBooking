package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controller.ControllerRetrieveTicketType;
import Entity.ticketType;

class BoundaryRetrieveTicketType extends JFrame implements ActionListener {
    private JTextField typeNameField;
    private JButton addButton;
    
    static ControllerRetrieveTicketType controllerRetrieve = new ControllerRetrieveTicketType();

    private String typeName;

    public BoundaryRetrieveTicketType() {
        setTitle("Retrieve Ticket Type");
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

        //get type name
        typeNameField = new JTextField();
        panel.add(typeNameField);

        addButton = new JButton("Find");
        addButton.addActionListener((ActionEvent e) -> {
            System.out.println("Finding Ticket Type...");
            
            // Get the entered values
            typeName = typeNameField.getText();
            
            //pass to controller
            ticketType ticketDetails = controllerRetrieve.retrieveTicketType(typeName);
            displayTicketObject(ticketDetails);
            // Close the dialog
            dispose();
        });
        panel.add(addButton);

        getContentPane().add(panel);
    }
    
    private void displayTicketObject(ticketType ticketDetails){
        if (ticketDetails != null) {
                JFrame frame = new JFrame("Ticket Type Details");
                frame.setSize(300, 200);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLayout(new GridLayout(3, 2));

                JLabel idLabel = new JLabel("ID:");
                JLabel typeNameLabels = new JLabel("Type Name:");
                JLabel priceLabel = new JLabel("Price:");

                JLabel idValueLabel = new JLabel(String.valueOf(ticketDetails.getid()));
                JLabel typeNameValueLabel = new JLabel(ticketDetails.getTypeName());
                JLabel priceValueLabel = new JLabel(String.valueOf(ticketDetails.getPrice()));

                frame.add(idLabel);
                frame.add(idValueLabel);
                frame.add(typeNameLabels);
                frame.add(typeNameValueLabel);
                frame.add(priceLabel);
                frame.add(priceValueLabel);

                frame.setVisible(true);
                System.out.println("Success!");
            } else {
                JOptionPane.showMessageDialog(this, "no Ticket Type record found");
                System.out.println("Failed.");
            }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}