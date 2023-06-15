package Boundary;

import Controller.UpdateUserProfileController;
import Entity.userProfile;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class UpdateUserProfileBoundary extends JFrame implements ActionListener {
    private JComboBox<String> profileComboBox;
    private JTextField newDescriptionTextField;

        public UpdateUserProfileBoundary() {
        setTitle("Update User Profile");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Profile ComboBox
        profileComboBox = new JComboBox<>();
        loadProfileComboBox();
        mainPanel.add(profileComboBox, BorderLayout.NORTH);

        // New Description Label
        JLabel newDescriptionLabel = new JLabel("New Description:");
        mainPanel.add(newDescriptionLabel, BorderLayout.WEST);

        // New Description Text Field
        newDescriptionTextField = new JTextField();
        mainPanel.add(newDescriptionTextField, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(this);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(updateButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }
          
 public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("Update")) {
            String newDescription = newDescriptionTextField.getText().trim();
            if (!newDescription.isEmpty()) {
                int selectedIndex = profileComboBox.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedProfile = (String) profileComboBox.getSelectedItem();
                    if (!selectedProfile.equals(newDescription)) {
                        boolean success = updateUserProfile(selectedProfile, newDescription);
                        if (success) {
                            JOptionPane.showMessageDialog(this, "Profile description updated!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            newDescriptionTextField.setText("");
                            profileComboBox.removeAllItems();
                            loadProfileComboBox();
                        } else {
                            JOptionPane.showMessageDialog(this, "Failed to update profile description.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Please enter a different description.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Please select a profile to update.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a new description.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (command.equals("Back")) {
            dispose();
        }
    }

    private void loadProfileComboBox() {
        try {
            java.util.List<userProfile> profiles = userProfile.getAllUserProfiles();
            if (!profiles.isEmpty()) {
                for (userProfile profile : profiles) {
                    
                    profileComboBox.addItem(profile.getDescription());
                }
            } else {
                JOptionPane.showMessageDialog(this, "No user profiles found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(this, "Failed to load user profiles.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean updateUserProfile(String profileName, String newDescription) {
        try {
            return UpdateUserProfileController.updateUserProfileController(profileName, newDescription);
        } catch (Exception e) {
            e.printStackTrace(System.out);
            return false;
        }
    }
}