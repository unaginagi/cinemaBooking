package Boundary;


import Controller.GetBookingListController;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class BoundaryGetBookings extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    
    GetBookingListController b = new GetBookingListController();
    int id;
    
    public BoundaryGetBookings(int UID){
        initComponents();
        ArrayList<String []> bookings = b.executeTask(UID);
        DefaultTableModel model = (DefaultTableModel) list.getModel();
        id = UID;
        for (String [] book : bookings)
        {
            model.addRow(new Object[]{book[0], book[1], book[2],
                book[3], book[4], book[5], book[6], book[7], book[8]});
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jScrollPane2 = new javax.swing.JScrollPane();
        list = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        select = new javax.swing.JButton();
        back = new javax.swing.JButton();
        create = new javax.swing.JButton();
        delete = new javax.swing.JButton();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText("jCheckBoxMenuItem2");

        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText("jCheckBoxMenuItem3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        list.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "BookingID", "RoomID", "SessionTiming", "UID", "ticketID", "quantity", "price", "bookdate"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        list.setColumnSelectionAllowed(true);
        list.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(list);
        list.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Bookings");

        select.setText("Select Booking");
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        create.setText("Create New Booking");
        create.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createActionPerformed(evt);
            }
        });

        delete.setText("Delete Booking");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(create)
                .addGap(90, 90, 90)
                .addComponent(select, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(delete, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(136, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(select)
                    .addComponent(create)
                    .addComponent(delete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(back)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        // TODO add your handling code here:
            
        int row = list.getSelectedRow();
        int column = 0;
        String value = (String)list.getValueAt(row, column);
        int BookingID1 = Integer.parseInt(value);
        BoundaryRetrieveBooking r = new BoundaryRetrieveBooking (BookingID1);
        r.setVisible(true);
    }//GEN-LAST:event_selectActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        DashboardCustomer dc = new DashboardCustomer(id);
        dc.setVisible(true);
        dispose();
        
    }//GEN-LAST:event_backActionPerformed

    private void createActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createActionPerformed
        // TODO add your handling code here:
        BoundaryCreateBooking bcb = new BoundaryCreateBooking(id);
        bcb.setVisible(true);
    }//GEN-LAST:event_createActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        int row = list.getSelectedRow();
        int column = 0;
        
        String value = (String)list.getValueAt(row, column);
        int BookingID1 = Integer.parseInt(value);
        
        value = (String) list.getValueAt(row, 3);
        int UID1 = Integer.parseInt(value);
        BoundaryDeleteBooking r = new BoundaryDeleteBooking (BookingID1, UID1);
        r.setVisible(true);
    }//GEN-LAST:event_deleteActionPerformed
    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(searchUserAccountGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(searchUserAccountGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(searchUserAccountGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(searchUserAccountGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new searchUserAccountGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton create;
    private javax.swing.JButton delete;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable list;
    private javax.swing.JButton select;
    // End of variables declaration//GEN-END:variables
}
