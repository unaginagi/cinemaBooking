package Boundary;

import Controller.deleteprebookcontroller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deleteprebookboundary extends JFrame {
    private JTextField fsalesIDField;
    private deleteprebookcontroller c1;

    public deleteprebookboundary(int id) {
        c1 = new deleteprebookcontroller();

        setTitle("Delete Prebooked Food");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        int fsalesID = id;

        try {
            boolean success = c1.deleteprebookfood(fsalesID);
            if (success) {
                JOptionPane.showMessageDialog(null, "Prebooked food deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete prebooked food.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
        }
    }
}

