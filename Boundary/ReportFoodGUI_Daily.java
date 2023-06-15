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
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReportFoodGUI_Daily extends JFrame implements ActionListener {
    private JLabel messageLabel;
    private JComboBox<String> dayDropdown;
    private JComboBox<String> monthDropdown;
    private JComboBox<String> yearDropdown;
    private JButton generateButton;
    private JButton backButton;

    public ReportFoodGUI_Daily() {
        setTitle("Daily Food Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 1.0; // Equal horizontal space allocation

        messageLabel = new JLabel("Please enter your date:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 3;
        panel.add(messageLabel, constraints);

        List<String> days = new ArrayList<>();
        days.add("Day");
        for (int i = 1; i <= 31; i++) {
            days.add(String.valueOf(i));
        }
        dayDropdown = new JComboBox<>(days.toArray(new String[0]));

        List<String> months = new ArrayList<>();
        months.add("Month");
        for (int i = 1; i <= 12; i++) {
            months.add(String.valueOf(i));
        }
        monthDropdown = new JComboBox<>(months.toArray(new String[0]));

        int currentYear = LocalDate.now().getYear();
        List<String> years = new ArrayList<>();
        years.add("Year");
        for (int i = currentYear - 10; i <= currentYear + 10; i++) {
            years.add(String.valueOf(i));
        }
        yearDropdown = new JComboBox<>(years.toArray(new String[0]));

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(dayDropdown, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(monthDropdown, constraints);

        constraints.gridx = 2;
        constraints.gridy = 1;
        panel.add(yearDropdown, constraints);

        generateButton = new JButton("Generate");
        generateButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        constraints.fill = GridBagConstraints.HORIZONTAL; // Set buttons to fill horizontally

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        panel.add(generateButton, constraints);

        constraints.gridx = 2;
        constraints.gridy = 2;
        panel.add(backButton, constraints);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            String selectedDay = (String) dayDropdown.getSelectedItem();
            String selectedMonth = (String) monthDropdown.getSelectedItem();
            String selectedYear = (String) yearDropdown.getSelectedItem();

            if (selectedDay.equals("Day") || selectedMonth.equals("Month") || selectedYear.equals("Year")) {
                // Handle error, display message to select valid date
                JOptionPane.showMessageDialog(this, "Please select a valid date", "Alert", JOptionPane.WARNING_MESSAGE);
                return;
            }
            
             //padding selectedMonth
            if (Integer.valueOf(selectedMonth)< 10){
                selectedMonth = "0" + selectedMonth;
            }

            String selectedDate = selectedYear + "-" + selectedMonth + "-" + selectedDay;
            System.out.println("Selected date: " + selectedDate);

            // TODO: Perform logic for generating daily booking report using the selected date
            String dailyReport = Controller.GenerateReportFoodDailyController.generateReportFoodDailyController(selectedDate);
            JTextArea reportTextArea = new JTextArea(dailyReport.toString());
            reportTextArea.setEditable(false);
            JOptionPane.showMessageDialog(null, new JScrollPane(reportTextArea),
                    "Daily Report", JOptionPane.PLAIN_MESSAGE);

        } else if (e.getSource() == backButton) {
            // Handle back button action
            dispose(); // Close the current window
            // Code to navigate back to the previous page or main menu
        }
    }
}

