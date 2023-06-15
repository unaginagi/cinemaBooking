
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

public class ReviewAndRatingGUI extends JFrame implements ActionListener{
    // Define your UI components here
    static ControllerSearchTicketType controllerSearch = new ControllerSearchTicketType();
    int thisUID;
    
        public ReviewAndRatingGUI(int UID) {
                // Initialize the UI components and configure the frame
                setTitle("Tickt Type Dashboard");
                setDefaultCloseOperation(EXIT_ON_CLOSE);
                setSize(600, 400);
                setLocationRelativeTo(null);
                setResizable(false);
                thisUID = UID;
                initComponents();
                
            }
        
  private void initComponents() {
            
            JButton createReviewAndRatingButton = new JButton("Create Review And Rating");
            JButton retrieveReviewAndRatingButton = new JButton("Retrieve Review And Rating");
            JButton logoutButton = new JButton("Back");
        
            createReviewAndRatingButton.addActionListener(this);
            retrieveReviewAndRatingButton.addActionListener(this);
            logoutButton.addActionListener(this);
        
            JPanel mainPanel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.fill = GridBagConstraints.HORIZONTAL;
            gbc.insets = new Insets(3, 3, 3, 3);
            gbc.weightx = 1.0; // Equal horizontal weight for all buttons

            gbc.gridx = 0;
            gbc.gridy = 0;
            mainPanel.add(createReviewAndRatingButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            mainPanel.add(retrieveReviewAndRatingButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            mainPanel.add(logoutButton, gbc);

            add(mainPanel);
            SwingUtilities.updateComponentTreeUI(this);
    }
        
    public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Back")) {
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new DashboardCustomer(thisUID).setVisible(true);
                }
                });
                dispose();
            } else if (e.getActionCommand().equals("Create Review And Rating")) {
                // Handle create Ticket Type button action
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    BoundaryCreateReviewAndRating createReviewAndRating = new BoundaryCreateReviewAndRating(thisUID);
                    createReviewAndRating.setVisible(true);
                }
            });

            } else if (e.getActionCommand().equals("Retrieve Review And Rating")) {
                // Handle retrieve Ticket Type button action
                SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    BoundaryRetrieveReviewAndRating retrieveReviewAndRating = new BoundaryRetrieveReviewAndRating();
                    retrieveReviewAndRating.setVisible(true);
                }
            });
            }
    }       
}
