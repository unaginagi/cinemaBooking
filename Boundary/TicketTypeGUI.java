package Boundary;

import java.lang.System;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import Controller.ControllerSearchTicketType;
import Entity.ticketType;


public class TicketTypeGUI extends JFrame implements ActionListener {
    // Define your UI components here
    static ControllerSearchTicketType controllerSearch = new ControllerSearchTicketType();
    
        public TicketTypeGUI() {
            // Initialize the UI components and configure the frame
            setTitle("Tickt Type Dashboard");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(600, 400);
            setLocationRelativeTo(null);
            setResizable(false);
            initComponents();
        }

        private void initComponents() {
            
            JButton createTicketButton = new JButton("Create Ticket Type");
            JButton retrieveTicketButton = new JButton("Retrieve Ticket Type");
            JButton updateTicketButton = new JButton("Update Ticket Type");
            JButton deleteTicketButton = new JButton("Delete Ticket Type");
            JButton searchTicketButton = new JButton("Search Ticket Type");
            JButton logoutButton = new JButton("Back");
        
            createTicketButton.addActionListener(this);
            retrieveTicketButton.addActionListener(this);
            updateTicketButton.addActionListener(this);
            deleteTicketButton.addActionListener(this);
            searchTicketButton.addActionListener(this);
            logoutButton.addActionListener(this);
        
            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.weightx = 1.0; // Equal horizontal weight for all buttons

            gbc.gridx = 0;
            gbc.gridy = 0;
            mainPanel.add(createTicketButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            mainPanel.add(retrieveTicketButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            mainPanel.add(updateTicketButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            mainPanel.add(deleteTicketButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            mainPanel.add(searchTicketButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            mainPanel.add(logoutButton, gbc);

            
            JTable table = populateTable();
            
            gbc.gridx = 0;
            gbc.gridy = 6;
            mainPanel.add(table,gbc);
                
            add(mainPanel);
            SwingUtilities.updateComponentTreeUI(this);
    }
        
        public JTable populateTable(){
            // Create a table model to hold the data
            DefaultTableModel tableModel = new DefaultTableModel();
            tableModel.addColumn("ID");
            tableModel.addColumn("Type Name");
            tableModel.addColumn("Price");

            Object[] header = {"ID", "Type Name", "Price"};
            tableModel.addRow(header);
            
            // Populate the table model with the ticket data
            List<ticketType> listTicket = controllerSearch.searchTicketType();
            for (ticketType ticket : listTicket) {
                Object[] rowData = {ticket.getid(), ticket.getTypeName(), ticket.getPrice()};
                tableModel.addRow(rowData);
            }

            // Create the JTable with the table model
            JTable table = new JTable(tableModel);
            
            return table;
        }
        
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Back")) {
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new DashboardCinemaManager().setVisible(true);
                }
                });
                dispose();
            } else if (e.getActionCommand().equals("Create Ticket Type")) {
                // Handle create Ticket Type button action
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    BoundaryCreateTicketType createTicketType = new BoundaryCreateTicketType();
                    createTicketType.setVisible(true);
                }
            });

            } else if (e.getActionCommand().equals("Retrieve Ticket Type")) {
                // Handle retrieve Ticket Type button action
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    BoundaryRetrieveTicketType retrieveTicketType = new BoundaryRetrieveTicketType();
                    retrieveTicketType.setVisible(true);
                }
            });
            } else if (e.getActionCommand().equals("Update Ticket Type")) {
                // Handle update Ticket Type button action
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    BoundaryUpdateTicketType updateTicketType = new BoundaryUpdateTicketType();
                    updateTicketType.setVisible(true);
                }
            });
            } else if (e.getActionCommand().equals("Delete Ticket Type")) {
                // Handle delete Ticket Type button action
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    BoundaryDeleteTicketType DeleteTicketType = new BoundaryDeleteTicketType();
                    DeleteTicketType.setVisible(true);
                    }
                });

            } else if (e.getActionCommand().equals("Search Ticket Type")) {
                // Handle search Ticket Type button action
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    BoundarySearchTicketType searchTicketType = new BoundarySearchTicketType();
                    searchTicketType.setVisible(true);
                    }
                });
            }  
             
        }
}