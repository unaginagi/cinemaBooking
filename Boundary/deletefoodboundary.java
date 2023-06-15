package Boundary;

import Controller.deletefoodcontroller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deletefoodboundary extends JFrame implements ActionListener{
    private JTextField idField;
    private deletefoodcontroller c1;

    public deletefoodboundary() {
        c1 = new deletefoodcontroller();
        
        setTitle("Food and Beverage Management");
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());
        
        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);
        
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                
                try {
                    boolean success = c1.deletefoodItem(id);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Food item deleted successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete food item.");
                    }
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
                }
                
            }
        });
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(deleteButton);
        
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    /*
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new deletefoodboundary();
            }
        });
    }*/

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
