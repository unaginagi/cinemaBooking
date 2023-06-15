package Boundary;

import Controller.CreateUserProfileController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.SQLException;

public class CreateUserProfileBoundary extends JFrame implements ActionListener {
    private JTextField profileNameTextField;

    public CreateUserProfileBoundary() {
        setTitle("Create User Profile");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Profile Name Label
        JLabel profileNameLabel = new JLabel("Profile Name:");
        mainPanel.add(profileNameLabel, BorderLayout.NORTH);

        // Profile Name Text Field
        profileNameTextField = new JTextField();
        profileNameTextField.addActionListener(this);
        mainPanel.add(profileNameTextField, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addButton = new JButton("Add");
        addButton.addActionListener(this);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);
        buttonPanel.add(addButton);
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();

        if (command.equals("Add")) {
            String profileName = profileNameTextField.getText().trim();
            if (!profileName.isEmpty()) {
                try{
                    CreateUserProfileController.addUserProfileController(profileName);
                    JOptionPane.showMessageDialog(this, "Profile added!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    profileNameTextField.setText("");
                }
                catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
                } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);      
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a profile name.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (command.equals("Back")) {
            dispose(); // Close the create profile page
        }
    }
}

