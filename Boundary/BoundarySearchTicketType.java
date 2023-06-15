package Boundary;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import Controller.ControllerSearchTicketType;
import Entity.ticketType;


class BoundarySearchTicketType extends JFrame implements ActionListener {
    private JTextField minPriceField;
    private JTextField maxPriceField;
    private JButton addButton;
    
    static ControllerSearchTicketType controllerSearch = new ControllerSearchTicketType();

    private double minPrice;
    private double maxPrice;

    public BoundarySearchTicketType() {
        setTitle("Search Ticket Type");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        JLabel minPriceLabel = new JLabel("Minimum Price:");
        panel.add(minPriceLabel);

        minPriceField = new JTextField();
        panel.add(minPriceField);
        
        JLabel maxPriceLabel = new JLabel("Maximum Price:");
        panel.add(maxPriceLabel);

        maxPriceField = new JTextField();
        panel.add(maxPriceField);

        addButton = new JButton("Search");
        addButton.addActionListener((ActionEvent e) -> {
            System.out.println("Finding Ticket Type...");
            
            // Get the entered values
            minPrice = Double.parseDouble(minPriceField.getText());
            maxPrice = Double.parseDouble(maxPriceField.getText());
            
            List<ticketType> listTicket = controllerSearch.searchTicketType(minPrice, maxPrice);
            displayTicketTypeList(listTicket);
            
            // Close the dialog
            dispose();
        });
        panel.add(addButton);

        getContentPane().add(panel);
    }


    private void displayTicketTypeList(List<ticketType> listTicket){
        if (listTicket != null) {
                // Create a table model to hold the data
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("ID");
                tableModel.addColumn("Type Name");
                tableModel.addColumn("Price");

                // Populate the table model with the ticket data
                for (ticketType ticket : listTicket) {
                    Object[] rowData = {ticket.getid(), ticket.getTypeName(), ticket.getPrice()};
                    tableModel.addRow(rowData);
                }

                // Create the JTable with the table model
                JTable table = new JTable(tableModel);

                // Place the table in a scroll pane for scrolling if needed
                JScrollPane scrollPane = new JScrollPane(table);

                // Display the table in a dialog or frame
                JOptionPane.showMessageDialog(this, scrollPane, "Ticket Types", JOptionPane.PLAIN_MESSAGE);
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