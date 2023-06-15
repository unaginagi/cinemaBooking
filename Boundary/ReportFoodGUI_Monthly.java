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
import javax.swing.SwingUtilities;

public class ReportFoodGUI_Monthly extends JFrame implements ActionListener {
    private JLabel monthLabel;
    private JComboBox<String> monthDropdown;
    private JLabel yearLabel;
    private JComboBox<String> yearDropdown;
    private JButton generateButton;
    private JButton backButton;

    public ReportFoodGUI_Monthly() {
        setTitle("Monthly Food Report");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 1.0; // Equal horizontal space allocation

        monthLabel = new JLabel("Please enter your month:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(monthLabel, constraints);

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};
        monthDropdown = new JComboBox<>(months);
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(monthDropdown, constraints);

        yearLabel = new JLabel("Please enter your year:");
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(yearLabel, constraints);

        int currentYear = LocalDate.now().getYear();
        String[] years = new String[10];
        for (int i = 0; i < 10; i++) {
            years[i] = String.valueOf(currentYear - i);
        }
        yearDropdown = new JComboBox<>(years);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(yearDropdown, constraints);

        generateButton = new JButton("Generate");
        generateButton.addActionListener(this);
        backButton = new JButton("Back");
        backButton.addActionListener(this);

        constraints.fill = GridBagConstraints.HORIZONTAL; // Set buttons to fill horizontally

        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(generateButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(backButton, constraints);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            String selectedMonth = (String) monthDropdown.getSelectedItem();
            int selectedYear = Integer.parseInt((String) yearDropdown.getSelectedItem());

            // Convert month name to number
            int selectedMonthNumber = convertMonthToNumber(selectedMonth);
String selectMonthString = "";
            //padding selectedMonth
            if (selectedMonthNumber< 10){
                selectMonthString = "0" + selectedMonthNumber;
            } else {
                selectMonthString = Integer.toString(selectedMonthNumber);
            }
            String selectYearString = Integer.toString(selectedYear);
            // TODO: Perform generation of monthly booking report based on selectedMonthNumber and selectedYear
            System.out.println("Generating monthly report for " + selectedMonthNumber + " " + selectedYear);
            
            String monthlyReport = Controller.GenerateReportFoodMonthlyController.generateReportFoodMonthlyController(selectMonthString,selectYearString);
            JTextArea reportTextArea = new JTextArea(monthlyReport.toString());
            reportTextArea.setEditable(false);
            JOptionPane.showMessageDialog(null, new JScrollPane(reportTextArea),
                    "Monthly Report", JOptionPane.PLAIN_MESSAGE);
        
        } else if (e.getSource() == backButton) {
            // TODO: Handle back button action
            dispose();
        }
    }

    private int convertMonthToNumber(String month) {
        switch (month) {
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
    }
}