
package Boundary;

import Controller.ControllerCreateReviewAndRating;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
        
public class BoundaryCreateReviewAndRating extends JFrame implements ActionListener{
    private JTextField ReviewField;
    private JTextField RatingField;
    private JButton submitButton;
        
    static ControllerCreateReviewAndRating controllerCreate = new ControllerCreateReviewAndRating();
        
    private String Review;
    private int Rating;
    
    public BoundaryCreateReviewAndRating(int UID) {
        setTitle("Create Rating");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setResizable(false);
    
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2));
    
        JLabel reviewLabel = new JLabel("Review:");
        panel.add(reviewLabel);
        
        ReviewField = new JTextField();
        panel.add(ReviewField);
        
        JLabel ratingLabel = new JLabel("Rating : (1~5)");
        panel.add(ratingLabel);
        
        RatingField = new JTextField();
        panel.add(RatingField);
        
        submitButton = new JButton("Submit");
        submitButton.addActionListener((ActionEvent e) ->{
        
            System.out.println("Submitting...");
            
            // Get entered values
            Review = ReviewField.getText();
            Rating = Integer.parseInt(RatingField.getText());
            
            boolean result = controllerCreate.createReviewAndRating(Review,Rating,UID);
            displayMsg(result);
            
            // Close dialog
            dispose();
        });
    
        panel.add(submitButton);
        
        getContentPane().add(panel);
    }

    private void displayMsg(boolean result){
        if (result) {
            JOptionPane.showMessageDialog(this,"Review and Rating Submitted");
            System.out.println("Success"); 
        } else {
            JOptionPane.showMessageDialog(this,"Review and Rating didn't submit");
            System.out.println("Failed"); 
        }  
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
        
   