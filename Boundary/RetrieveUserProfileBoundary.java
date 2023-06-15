package Boundary;

import Entity.userProfile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RetrieveUserProfileBoundary extends JFrame implements ActionListener {
    private JTextField profileIDTextField;

    public RetrieveUserProfileBoundary() {
        setTitle("Retrieve User Profile");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Profile ID Label
        JLabel profileIDLabel = new JLabel("Profile ID:");
        mainPanel.add(profileIDLabel, BorderLayout.NORTH);

        // Profile ID Text Field
        profileIDTextField = new JTextField();
        profileIDTextField.addActionListener(this);
        mainPanel.add(profileIDTextField, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton retrieveButton = new JButton("Retrieve");
        retrieveButton.addActionListener(this);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(retrieveButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if (command.equals("Retrieve")) {
            String profileIDString = profileIDTextField.getText().trim();
            if (!profileIDString.isEmpty()) {
                try {
                    int profileID = Integer.parseInt(profileIDString);
                    userProfile profile = userProfile.getUserProfile(profileID);
                    if (profile != null && profile.getProfileID()!= 0) {
                        // Display the retrieved profile details
                        String message = "Profile ID: " + profile.getProfileID() + "\nDescription: " + profile.getDescription();
                        JOptionPane.showMessageDialog(this, message, "Profile Details", JOptionPane.INFORMATION_MESSAGE);
                        profileIDTextField.setText("");
                    } else {
                        JOptionPane.showMessageDialog(this, "No profile found!", "Error", JOptionPane.ERROR_MESSAGE);
                    } 
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(this, "Invalid profile ID. Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
                } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);      
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a profile ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (command.equals("Back")) {
            dispose(); // Close the retrieve profile page
        }
    }
  }  
/*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RetrieveUserProfileBoundary retrieveProfilePage = new RetrieveUserProfileBoundary();
                retrieveProfilePage.setVisible(true);
            }
        });
    }
*/



