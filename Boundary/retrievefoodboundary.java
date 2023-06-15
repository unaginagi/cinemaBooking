package Boundary;

import Controller.retrievefoodcontroller;
import Entity.foodItem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class retrievefoodboundary extends JFrame {
    private JTextField idField;
    private JTextArea resultArea;
    private retrievefoodcontroller c1;

    public retrievefoodboundary() {
        c1 = new retrievefoodcontroller();
        
        setTitle("Food and Beverage Management");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        
        JButton retrieveButton = new JButton("Retrieve");
        retrieveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                System.out.println(id);
                try {
                    foodItem item = c1.retrievefoodItem(id);
                    if (item != null) {
                        resultArea.setText("Name: " + item.getName() + "\nDescription: " + item.getDescription()
                            + "\nPrice: " + item.getPrice());
                    } else {
                        resultArea.setText("Food item not found.");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
                }
            }
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(retrieveButton);
        
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(resultArea), BorderLayout.SOUTH);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new retrievefoodboundary();
            }
        });
    }*/
}
