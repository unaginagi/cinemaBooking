package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoundaryPrepurchaseFnBDialog extends JDialog {
    private boolean answer;

    public BoundaryPrepurchaseFnBDialog(JFrame parent, String message) {
        super(parent, "Pre-purchase food?", true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);

        // Create the message label
        JLabel label = new JLabel(message);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the buttons
        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        // Add action listeners to the buttons
        yesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer = true;
                dispose();
            }
        });

        noButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                answer = false;
                dispose();
            }
        });

        // Create a panel to hold the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(yesButton);
        buttonPanel.add(noButton);

        // Create a panel to hold the label and button panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(label, BorderLayout.CENTER);
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Set the layout and add the content panel to the dialog
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        // Set the size and display the dialog
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public boolean getAnswer() {
        return answer;
    }
}
