package Boundary;

import Controller.searchfoodcontroller;
import Entity.foodItem;
import java.lang.System;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;


public class fnbGUI extends JFrame implements ActionListener {
    // Define your UI components here
    static searchfoodcontroller controllerSearch = new searchfoodcontroller();
    private JTable table;
    private DefaultTableModel tableModel = new DefaultTableModel();;

        public fnbGUI(){
            // Initialize the UI components and configure the frame
            setTitle("Food n Beverage Dashboard");
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setSize(600, 400);
            setLocationRelativeTo(null);
            setResizable(false);
            
            JButton createFnBButton = new JButton("Create FnB");
            JButton retrieveFnBButton = new JButton("Retrieve FnB");
            JButton updateFnBButton = new JButton("Update FnB");
            JButton deleteFnBButton = new JButton("Delete FnB");
            JButton searchFnBButton = new JButton("Search FnB");
            JButton logoutButton = new JButton("Back");
        
            createFnBButton.addActionListener(this);
            retrieveFnBButton.addActionListener(this);
            updateFnBButton.addActionListener(this);
            deleteFnBButton.addActionListener(this);
            searchFnBButton.addActionListener(this);
            logoutButton.addActionListener(this);
        
            // Create the table using the table model
            table = new JTable(tableModel);
            
            
            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.weightx = 1.0; // Equal horizontal weight for all buttons

            gbc.gridx = 0;
            gbc.gridy = 0;
            mainPanel.add(createFnBButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            mainPanel.add(retrieveFnBButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            mainPanel.add(updateFnBButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            mainPanel.add(deleteFnBButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 4;
            mainPanel.add(searchFnBButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 5;
            mainPanel.add(logoutButton, gbc);
            
            gbc.gridx = 0;
            gbc.gridy = 6;
            mainPanel.add(table, gbc);
            
            // Populate the table model with data from the database
            populateTable();    
            
            add(mainPanel);
            
            

    }
        
        private void populateTable(){
            // Clear the existing data in the table model
            tableModel.setRowCount(0);
            tableModel.setColumnCount(0);
            
            List<foodItem> listFnB = controllerSearch.searchfoodItem("");
            Object[] header = {"ID", "Name", "Description", "Price"};
            
            // Set the header in the table model
            tableModel.setColumnIdentifiers(header);
            tableModel.addRow(header);
            // Populate the table model with the ticket data
            
            for (foodItem fnb : listFnB) {
                Object[] rowData = {fnb.getId(), fnb.getName(), fnb.getDescription(), fnb.getPrice()};
                tableModel.addRow(rowData);
            }
        }
        
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Back")) {
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new DashboardCinemaManager().setVisible(true);
                }
                });
                dispose();
            } else if (e.getActionCommand().equals("Create FnB")) {
                // Handle create FnB button action
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    createfoodboundary createFnB = new createfoodboundary();
                    createFnB.setVisible(true);
                }
            });
                populateTable();
            } else if (e.getActionCommand().equals("Retrieve FnB")) {
                // Handle retrieve FnB button action
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    retrievefoodboundary retrieveFnB = new retrievefoodboundary();
                    retrieveFnB.setVisible(true);
                }
            });
                populateTable();
            } else if (e.getActionCommand().equals("Update FnB")) {
                // Handle update FnB button action
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    updatefoodboundary updateFnB = new updatefoodboundary();
                    updateFnB.setVisible(true);
                }
            });
                populateTable();
            } else if (e.getActionCommand().equals("Delete FnB")) {
                // Handle delete FnB button action
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    deletefoodboundary DeleteFnB = new deletefoodboundary();
                    DeleteFnB.setVisible(true);
                    }
                });
                populateTable();
            } else if (e.getActionCommand().equals("Search FnB")) {
                // Handle search FnB button action
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    searchfoodboundary searchFnB = new searchfoodboundary();
                    searchFnB.setVisible(true);
                    }
                });
                populateTable();
            }  
             
        }
}