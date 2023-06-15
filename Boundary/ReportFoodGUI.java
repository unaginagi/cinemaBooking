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

public class ReportFoodGUI extends JFrame implements ActionListener {
    private JLabel messageLabel;
    private JButton dailyButton;
    private JButton weeklyButton;
    private JButton monthlyButton;
    private JButton backButton;

    public ReportFoodGUI() {
        setTitle("Food Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 1.0; // Equal horizontal space allocation

        messageLabel = new JLabel("Daily/Weekly/Monthly food?");
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
            // TODO: Handle daily food report button action
            ReportFoodGUI_Daily foodReportGUI = new ReportFoodGUI_Daily();
            foodReportGUI.setVisible(true);
        } else if (e.getSource() == weeklyButton) {
            // TODO: Handle weekly food report button action
            ReportFoodGUI_Weekly foodReportGUI = new ReportFoodGUI_Weekly();
            foodReportGUI.setVisible(true);
        } else if (e.getSource() == monthlyButton) {
            // TODO: Handle monthly food report button action
            ReportFoodGUI_Monthly foodReportGUI = new ReportFoodGUI_Monthly();
            foodReportGUI.setVisible(true);
        } else if (e.getSource() == backButton) {
            // TODO: Handle back button action
            new DashboardCinemaOwner().setVisible(true);
            dispose();
        }
    }
}
