package Boundary;

import Controller.SearchUserProfileController;
import Entity.userProfile;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class SearchUserProfileBoundary extends JFrame implements ActionListener {
    private JTextField searchTextField;
    private JTable userProfileTable;
    private DefaultTableModel tableModel;

    public SearchUserProfileBoundary() {
        setTitle("Search User Profile");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Search Bar Panel
        JPanel searchBarPanel = new JPanel(new BorderLayout());
        searchBarPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        searchTextField = new JTextField();
        searchBarPanel.add(searchTextField, BorderLayout.CENTER);

        // Search Button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(this);
        searchBarPanel.add(searchButton, BorderLayout.EAST);
        mainPanel.add(searchBarPanel, BorderLayout.NORTH);

        // User Profile Table
        userProfileTable = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"Profile ID", "Description"}, 0);
        userProfileTable.setModel(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(userProfileTable);
        mainPanel.add(tableScrollPane, BorderLayout.CENTER);
        
        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(this);
        mainPanel.add(backButton, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Search")) {
            String searchDescription = searchTextField.getText().trim();
            try {
                List<userProfile> profiles = SearchUserProfileController.SearchUserProfileController(searchDescription);
                tableModel.setRowCount(0); // Clear table rows

                if (!profiles.isEmpty()) {
                    for (userProfile profile : profiles) {
                        Object[] rowData = {profile.getProfileID(), profile.getDescription()};
                        tableModel.addRow(rowData);
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No user profiles found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace(System.out);
                JOptionPane.showMessageDialog(this, "Failed to search user profiles.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getActionCommand().equals("Back")) {
            dispose(); // Close the create profile page
        }
    }
}
    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SearchUserProfileBoundary searchPage = new SearchUserProfileBoundary();
            searchPage.setVisible(true);
        });
    }*/
