package Boundary;

import Controller.searchfoodcontroller;
import Entity.foodItem;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class searchfoodboundary extends JFrame {
    private JTextField nameField;
    private JTextArea resultArea;
    private searchfoodcontroller c1;

    public searchfoodboundary() {
        c1 = new searchfoodcontroller();

        setTitle("Food and Beverage Management");
        setSize(300, 200);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();

                try {
                    List<foodItem> items = c1.searchfoodItem(name);
                    if (!items.isEmpty()) {
                        StringBuilder sb = new StringBuilder();
                        for (foodItem item : items) {
                            sb.append("Name: ").append(item.getName()).append("\n")
                                    .append("Description: ").append(item.getDescription()).append("\n")
                                    .append("Price: ").append(item.getPrice()).append("\n\n");
                        }
                        resultArea.setText(sb.toString());
                    } else {
                        resultArea.setText("No food items found.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
                }
            }
        });

        resultArea = new JTextArea();

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(searchButton);

        add(inputPanel, BorderLayout.NORTH);
        add(resultArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new searchfoodboundary().setVisible(true);
            }
        });
    }
}
