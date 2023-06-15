
package Boundary;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReportFoodGUI_Weekly extends JFrame implements ActionListener {
    private JLabel startDateLabel;
    private JComboBox<String> startDayDropdown;
    private JComboBox<String> startMonthDropdown;
    private JComboBox<String> startYearDropdown;

    private JLabel endDateLabel;
    private JComboBox<String> endDayDropdown;
    private JComboBox<String> endMonthDropdown;
    private JComboBox<String> endYearDropdown;

    private JButton generateButton;
    private JButton backButton;

    public ReportFoodGUI_Weekly() {
         setTitle("Weekly Food Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 1.0; // Equal horizontal space allocation

        startDateLabel = new JLabel("Please enter your start date:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        panel.add(startDateLabel, constraints);

        List<String> days = new ArrayList<>();
        days.add("Day");
        for (int i = 1; i <= 31; i++) {
            days.add(String.valueOf(i));
        }
        startDayDropdown = new JComboBox<>(days.toArray(new String[0]));

        List<String> months = new ArrayList<>();
        months.add("Month");
        for (int i = 1; i <= 12; i++) {
            months.add(String.valueOf(i));
        }
        startMonthDropdown = new JComboBox<>(months.toArray(new String[0]));

        int currentYear = LocalDate.now().getYear();
        List<String> years = new ArrayList<>();
        years.add("Year");
        for (int i = currentYear - 10; i <= currentYear + 10; i++) {
            years.add(String.valueOf(i));
        }
        startYearDropdown = new JComboBox<>(years.toArray(new String[0]));

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(startDayDropdown, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(startMonthDropdown, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        panel.add(startYearDropdown, constraints);
        
        endDateLabel = new JLabel("Please enter your end date:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 3;
        panel.add(endDateLabel, constraints);

        endDayDropdown = new JComboBox<>(days.toArray(new String[0]));
        endMonthDropdown = new JComboBox<>(months.toArray(new String[0]));
        endYearDropdown = new JComboBox<>(years.toArray(new String[0]));

        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(endDayDropdown, constraints);

        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(endMonthDropdown, constraints);

        constraints.gridx = 2;
        constraints.gridy = 3;
        panel.add(endYearDropdown, constraints);

        generateButton = new JButton("Generate");
        generateButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        constraints.fill = GridBagConstraints.HORIZONTAL; // Set buttons to fill horizontally

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        panel.add(generateButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(backButton, constraints);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            // Handle generate button action
            String startDay = (String) startDayDropdown.getSelectedItem();
            String startMonth = (String) startMonthDropdown.getSelectedItem();
            String startYear = (String) startYearDropdown.getSelectedItem();

            String endDay = (String) endDayDropdown.getSelectedItem();
            String endMonth = (String) endMonthDropdown.getSelectedItem();
            String endYear = (String) endYearDropdown.getSelectedItem();

            if (startDay.equals("Day") || startMonth.equals("Month") || startYear.equals("Year") ||
                    endDay.equals("Day") || endMonth.equals("Month") || endYear.equals("Year")) {
                // Handle error: User didn't select all the necessary values
                JOptionPane.showMessageDialog(this, "Please select all the necessary values", "Alert", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int startDayValue = Integer.parseInt(startDay);
            int startMonthValue = Integer.parseInt(startMonth);
            int startYearValue = Integer.parseInt(startYear);

            int endDayValue = Integer.parseInt(endDay);
            int endMonthValue = Integer.parseInt(endMonth);
            int endYearValue = Integer.parseInt(endYear);

            LocalDate startDate = LocalDate.of(startYearValue, startMonthValue, startDayValue);
            LocalDate endDate = LocalDate.of(endYearValue, endMonthValue, endDayValue);

         
           // Check if the difference between start and end date is exactly 7 days
            if (startDate.plusDays(7).isEqual(endDate)) {
                String startDateString = startDate.toString();
            String endDateString = endDate.toString();
            System.out.println("Start: " + startDateString + "; End: "+ endDateString);
            String weeklyReport = Controller.GenerateReportFoodWeeklyController.generateReportFoodWeeklyController(startDateString, endDateString);
            JTextArea reportTextArea = new JTextArea(weeklyReport.toString());
            reportTextArea.setEditable(false);
            JOptionPane.showMessageDialog(null, new JScrollPane(reportTextArea),
                    "Weekly Report", JOptionPane.PLAIN_MESSAGE);
                
            } else {
                // Handle error: Difference is not exactly 7 days
                JOptionPane.showMessageDialog(this, "The difference between start and end date must be exactly 7 days.", "Alert", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // TODO: Perform logic for generating weekly booking report using the selected dates

        } else if (e.getSource() == backButton) {
            // Handle back button action
            dispose(); // Close the current window
            // Code to navigate back to the previous page or main menu
        }
    }
}
