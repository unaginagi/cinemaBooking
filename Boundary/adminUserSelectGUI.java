package Boundary;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class adminUserSelectGUI extends JFrame implements ActionListener {
    private JLabel titleLabel;
    private JButton userProfileButton;
    private JButton userAccountButton;
    private JButton logoutButton;

    public adminUserSelectGUI() {
setTitle("Admin User Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.weightx = 1.0; // Equal horizontal space allocation

        titleLabel = new JLabel("<html><div style='text-align: center;'>Welcome User Admin!<br>What would you like to do today?</div></html>");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER; // Center horizontally
        panel.add(titleLabel, constraints);

        userProfileButton = new JButton("User Profile");
        userProfileButton.addActionListener(this);
        userAccountButton = new JButton("User Account");
        userAccountButton.addActionListener(this);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(this);

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        panel.add(userProfileButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(userAccountButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER; // Center horizontally
        panel.add(logoutButton, constraints);

        add(panel);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == userProfileButton) {
                    SwingUtilities.invokeLater(() -> {
                    UserProfileDashboard dashboardFrame = new UserProfileDashboard();
                    dashboardFrame.setVisible(true);
                    dispose();
                });
        } else if (e.getSource() == userAccountButton) {
            searchUserAccountGUI sua = new searchUserAccountGUI();
            sua.setVisible(true);
            dispose();
        } else if (e.getSource() == logoutButton) {
            int choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "Logout Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                // Perform any necessary logout actions
                JOptionPane.showMessageDialog(this, "Logout successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose(); // Close the dashboard window
                //open login
                SwingUtilities.invokeLater(LoginPage::new);
        }
    }
}
}
/*
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminUserSelectGUI().setVisible(true);
            }
        });
    }
}
*/
/*
package Boundary;
public class adminUserSelectGUI extends javax.swing.JFrame {

    public adminUserSelectGUI() {
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        userProfile = new javax.swing.JButton();
        userAccount = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        userProfile.setText("User Profile");
        userProfile.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userProfileActionPerformed(evt);
            }
        });

        userAccount.setText("User Account");
        userAccount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userAccountActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(userProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                .addComponent(userAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userProfile, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userAccount, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userProfileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userProfileActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_userProfileActionPerformed

    private void userAccountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userAccountActionPerformed
        // TODO add your handling code here:
        searchUserAccountGUI sua = new searchUserAccountGUI ();
        sua.setVisible(true);
        dispose();
    }//GEN-LAST:event_userAccountActionPerformed

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new adminUserSelectGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton userAccount;
    private javax.swing.JButton userProfile;
    // End of variables declaration//GEN-END:variables
}


*/
