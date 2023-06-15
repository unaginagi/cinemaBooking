
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
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class DashboardCustomer extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JButton bookTicketButton;
    private JButton FnBButton;
    private JButton viewReviewsButton;
    private JButton logoutButton;
    private int cUID;
    
    public DashboardCustomer(int UID) {
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);
        cUID = UID;

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 1.0; // Equal horizontal space allocation

        titleLabel = new JLabel("<html><div style='text-align: center;'>Welcome Customer!<br>What would you like to do today?</div></html>");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER; // Center horizontally
        panel.add(titleLabel, constraints);

        bookTicketButton = new JButton("Book Ticket");
        bookTicketButton.addActionListener(this);
        FnBButton = new JButton("Food & Beverages");
        FnBButton.addActionListener(this);
        viewReviewsButton = new JButton("View Cinema Reviews and Ratings");
        viewReviewsButton.addActionListener(this);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);

        constraints.fill = GridBagConstraints.HORIZONTAL; // Set buttons to fill horizontally

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(bookTicketButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(FnBButton, constraints);
        
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(viewReviewsButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 2;
        panel.add(logoutButton, constraints);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookTicketButton) {
            // Handle book ticket button action
            SwingUtilities.invokeLater(() -> {
                    BoundaryGetBookings dashboardFrame = new BoundaryGetBookings(cUID);
                    dashboardFrame.setVisible(true);
                    dispose();
                });
        }else if (e.getSource() == FnBButton) {
            // TODO: Handle Food & Beverages button action
            SwingUtilities.invokeLater(() -> {
                    BoundaryGetFnB dashboardFrame = new BoundaryGetFnB(cUID);
                    dashboardFrame.setVisible(true);
                    dispose();
                });
        } else if (e.getSource() == viewReviewsButton) {
            // TODO: Handle view movie reviews and ratings button action
            SwingUtilities.invokeLater(() -> {
                    ReviewAndRatingGUI dashboardFrame = new ReviewAndRatingGUI(cUID);
                    dashboardFrame.setVisible(true);
                    dispose();
                });
            
        } else if (e.getSource() == logoutButton) {
             int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Logout successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the dashboard window
                //open login
                SwingUtilities.invokeLater(LoginPage::new);
        }
        }
    }
}
/*
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardCustomer().setVisible(true);
            }
        });
    }
}*/
