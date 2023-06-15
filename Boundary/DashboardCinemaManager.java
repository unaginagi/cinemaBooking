package Boundary;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class DashboardCinemaManager extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JButton movieListingsButton;
    private JButton ticketTypeButton;
    private JButton foodBeveragesButton;
    private JButton logoutButton;

    public DashboardCinemaManager() {
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 500);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 1.0; // Equal horizontal space allocation

        titleLabel = new JLabel("<html><div style='text-align: center;'>Welcome Cinema Manager!<br>What would you like to do today?</div></html>");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER; // Center horizontally
        panel.add(titleLabel, constraints);

        movieListingsButton = new JButton("Movie Listings");
        movieListingsButton.addActionListener(this);
        ticketTypeButton = new JButton("Ticket Types");
        ticketTypeButton.addActionListener(this);
        foodBeveragesButton = new JButton("Food & Beverages");
        foodBeveragesButton.addActionListener(this);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);

        constraints.fill = GridBagConstraints.HORIZONTAL; // Set buttons to fill horizontally

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(movieListingsButton, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(ticketTypeButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(foodBeveragesButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(logoutButton, constraints);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == movieListingsButton) {
            // TODO: Handle movie button action
            SwingUtilities.invokeLater(() -> {
                    MovieListingGUI dashboardFrame = new MovieListingGUI();
                    dashboardFrame.setVisible(true);
                    dispose();
                });
        } else if (e.getSource() == ticketTypeButton) {
            // TODO: Handle ticket type button action
            SwingUtilities.invokeLater(() -> {
                    TicketTypeGUI dashboardFrame = new TicketTypeGUI();
                    dashboardFrame.setVisible(true);
                    dispose();
                });
        } else if (e.getSource() == foodBeveragesButton) {
            // TODO: Handle food and beverages button action
            SwingUtilities.invokeLater(() -> {
                    fnbGUI dashboardFrame = new fnbGUI();
                    dashboardFrame.setVisible(true);
                    dispose();
                });
        } else if (e.getSource() == logoutButton) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                // Perform any necessary logout actions
                JOptionPane.showMessageDialog(this, "Logout successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the dashboard window
                //open login
                SwingUtilities.invokeLater(LoginPage::new);
        }
        }
    }
}

