package Boundary;

import Controller.DeleteUserProfileController;
import Entity.userProfile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeleteUserProfileBoundary extends JFrame implements ActionListener {
    private JTextField profileNameTextField;
    private JComboBox<String> profileComboBox;

    public DeleteUserProfileBoundary() {
        setTitle("Delete User Profile");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Profile Name Label
        JLabel profileNameLabel = new JLabel("Profile Name:");
        mainPanel.add(profileNameLabel, BorderLayout.NORTH);

        // Profile ComboBox
        profileComboBox = new JComboBox<>();
        loadProfileComboBox(); // Populate the combo box with user profiles
        mainPanel.add(profileComboBox, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

     private void loadProfileComboBox() {
        try {
            List<userProfile> profiles = userProfile.getAllUserProfiles();
            for (userProfile profile : profiles) {
                profileComboBox.addItem(profile.getDescription());
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error loading profiles.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if (command.equals("Delete")) {
            String profileName = (String) profileComboBox.getSelectedItem();
            if (profileName != null) {
                int confirmation = JOptionPane.showConfirmDialog(this,
                        "Are you sure you want to delete '" + profileName + "'? "
                                + "\nThis cannot be undone!", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    // Delete profile logic here
                    try{
                        if (DeleteUserProfileController.deleteUserProfileController(profileName) == true){
                           JOptionPane.showMessageDialog(this, "Profile deleted!", "Success", JOptionPane.INFORMATION_MESSAGE);
                           profileComboBox.removeAllItems();
                           loadProfileComboBox(); // Refresh the profile combo box
                    } else {
                            JOptionPane.showMessageDialog(this, "Profile NOT deleted!", "Alert", JOptionPane.WARNING_MESSAGE);
                            profileComboBox.removeAllItems();
                            loadProfileComboBox(); // Refresh the profile combo box
                        }
                }
                catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
                } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);      
                }
                    
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a profile.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (command.equals("Back")) {
            dispose(); // Close the delete profile page
        }
    }
}