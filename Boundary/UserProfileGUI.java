package Boundary;
import Boundary.UpdateUserProfileBoundary;
import Boundary.SearchUserProfileBoundary;
import Boundary.RetrieveUserProfileBoundary;
import Boundary.DeleteUserProfileBoundary;
import Boundary.CreateUserProfileBoundary;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserProfileGUI extends JFrame implements ActionListener {
    
    public UserProfileGUI() {
        setTitle("Dashboard");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        
        JButton createProfileButton = new JButton("Create User Profile");
        JButton retrieveProfileButton = new JButton("Retrieve User Profile");
        JButton updateProfileButton = new JButton("Update User Profile");
        JButton deleteProfileButton = new JButton("Delete User Profile");
        JButton searchProfileButton = new JButton("Search User Profile");
        JButton logoutButton = new JButton("Back");
        
        createProfileButton.addActionListener(this);
        retrieveProfileButton.addActionListener(this);
        updateProfileButton.addActionListener(this);
        deleteProfileButton.addActionListener(this);
        searchProfileButton.addActionListener(this);
        logoutButton.addActionListener(this);

        //All buttons equal weight
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0; // Equal horizontal weight for all buttons

        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(createProfileButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(retrieveProfileButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(updateProfileButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(deleteProfileButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(searchProfileButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(logoutButton, gbc);

        add(mainPanel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Back")) {
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DashboardUserAdmin().setVisible(true);
            }
            });
            dispose();
        } else if (e.getActionCommand().equals("Create User Profile")) {
            // Handle create profile button action
            //JOptionPane.showMessageDialog(this, "Create User Profile button clicked!", "Info", JOptionPane.INFORMATION_MESSAGE);
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CreateUserProfileBoundary createProfilePage = new CreateUserProfileBoundary();
                createProfilePage.setVisible(true);
            }
        });
            
        } else if (e.getActionCommand().equals("Retrieve User Profile")) {
            // Handle retrieve profile button action
            //JOptionPane.showMessageDialog(this, "Retrieve User Profile button clicked!", "Info", JOptionPane.INFORMATION_MESSAGE);
             SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                RetrieveUserProfileBoundary retrieveProfilePage = new RetrieveUserProfileBoundary();
                retrieveProfilePage.setVisible(true);
            }
        });
        } else if (e.getActionCommand().equals("Update User Profile")) {
            // Handle update profile button action
            //JOptionPane.showMessageDialog(this, "Update User Profile button clicked!", "Info", JOptionPane.INFORMATION_MESSAGE);
             SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                UpdateUserProfileBoundary updateProfilePage = new UpdateUserProfileBoundary();
                updateProfilePage.setVisible(true);
            }
        });
        } else if (e.getActionCommand().equals("Delete User Profile")) {
            // Handle delete profile button action
            //JOptionPane.showMessageDialog(this, "Delete User Profile button clicked!", "Info", JOptionPane.INFORMATION_MESSAGE);
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DeleteUserProfileBoundary DeleteUserProfileBoundary = new DeleteUserProfileBoundary();
                DeleteUserProfileBoundary.setVisible(true);
                }
            });
            
        } else if (e.getActionCommand().equals("Search User Profile")) {
            // Handle search profile button action
            //JOptionPane.showMessageDialog(this, "Search User Profile button clicked!", "Info", JOptionPane.INFORMATION_MESSAGE);
            SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                SearchUserProfileBoundary searchPage = new SearchUserProfileBoundary();
                searchPage.setVisible(true);
                }
            });
        }
    }
}

