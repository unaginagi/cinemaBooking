/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Boundary;

/**
 *
 * @author ASUS
 */
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportBookingGUI extends JFrame implements ActionListener {
    private JLabel messageLabel;
    private JButton dailyButton;
    private JButton weeklyButton;
    private JButton monthlyButton;
    private JButton backButton;

    public ReportBookingGUI() {
        setTitle("Booking Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 1.0; // Equal horizontal space allocation

        messageLabel = new JLabel("Daily/Weekly/Monthly booking?");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        panel.add(messageLabel, constraints);

        dailyButton = new JButton("Daily");
        dailyButton.addActionListener(this);
        weeklyButton = new JButton("Weekly");
        weeklyButton.addActionListener(this);
        monthlyButton = new JButton("Monthly");
        monthlyButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        constraints.fill = GridBagConstraints.HORIZONTAL; // Set buttons to fill horizontally

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(dailyButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(weeklyButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        panel.add(monthlyButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        panel.add(backButton, constraints);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == dailyButton) {
            // TODO: Handle daily booking report button action
            ReportBookingGUI_Daily bookingReportGUI = new ReportBookingGUI_Daily();
            bookingReportGUI.setVisible(true);
        } else if (e.getSource() == weeklyButton) {
            // TODO: Handle weekly booking report button action
            ReportBookingGUI_Weekly bookingReportGUI = new ReportBookingGUI_Weekly();
            bookingReportGUI.setVisible(true);
        } else if (e.getSource() == monthlyButton) {
            // TODO: Handle monthly booking report button action
             ReportBookingGUI_Monthly bookingReportGUI = new ReportBookingGUI_Monthly();
            bookingReportGUI.setVisible(true);
        } else if (e.getSource() == backButton) {
            // TODO: Handle back button action
            new DashboardCinemaOwner().setVisible(true);
            dispose();
        }
    }
}
/*
    public static void main(String[] args) {
        ReportBookingGUI bookingReportGUI = new ReportBookingGUI();
        bookingReportGUI.setVisible(true);
    }
}*/

