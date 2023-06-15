
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

public class DashboardCinemaOwner extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JButton bookingReportButton;
    private JButton foodReportButton;
    private JButton logoutButton;

    public DashboardCinemaOwner() {
        setTitle("Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 1.0; // Equal horizontal space allocation

        titleLabel = new JLabel("<html><div style='text-align: center;'>Welcome Cinema Owner!<br>What would you like to do today?</div></html>");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER; // Center horizontally
        panel.add(titleLabel, constraints);

        bookingReportButton = new JButton("Booking Report");
        bookingReportButton.addActionListener(this);
        foodReportButton = new JButton("Food Report");
        foodReportButton.addActionListener(this);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);

        constraints.fill = GridBagConstraints.HORIZONTAL; // Set buttons to fill horizontally

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        panel.add(bookingReportButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(foodReportButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        panel.add(logoutButton, constraints);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookingReportButton) {
            // TODO: Handle booking report button action
            SwingUtilities.invokeLater(() -> {
                    ReportBookingGUI dashboardFrame = new ReportBookingGUI();
                    dashboardFrame.setVisible(true);
                    dispose();
                });
        } else if (e.getSource() == foodReportButton) {
            // TODO: Handle food report button action
            SwingUtilities.invokeLater(() -> {
                    ReportFoodGUI dashboardFrame = new ReportFoodGUI();
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
                new DashboardCinemaOwner().setVisible(true);
            }
        });
    }
}
*/