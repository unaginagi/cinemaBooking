
package Boundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Controller.ControllerRetrieveReviewAndRating;
import Entity.ReviewAndRating;
import java.util.ArrayList;
import java.util.List;

public class BoundaryRetrieveReviewAndRating extends JFrame implements ActionListener{
    private JTextField ReviewField;
    private JTextField RatingField;
    private JButton getReviewButton;
        
    static ControllerRetrieveReviewAndRating controllerRetrieve = new ControllerRetrieveReviewAndRating();
        
    private String Review;
    private int Rating;
    
    public BoundaryRetrieveReviewAndRating() {
        setTitle("Retrieve Review and Rating");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1200, 1000);
        setLocationRelativeTo(null);
        setResizable(false);
        List<ReviewAndRating> reviews = new ArrayList<>();
        reviews = controllerRetrieve.retrieveReviewAndRating();
        
        if(reviews != null) {
            
            List<ReviewAndRating> reviewAndRatingList = reviews;
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            int totalRating = 0;
            int counter = 0;
            for (ReviewAndRating reviewAndRating : reviewAndRatingList) {
                String review = reviewAndRating.getReview();
                int rating = reviewAndRating.getRating();
                totalRating = totalRating + rating;
                counter++;
                JLabel ratinglabel = new JLabel("Rating: " + rating );
                panel.add(ratinglabel);
                JLabel reviewlabel = new JLabel("Review: " + review + "     ");
                panel.add(reviewlabel);
            }
            double average = totalRating/counter;
                JLabel emptylabel = new JLabel("=-=-=-=-=-=-=-=-=-=-=");
                panel.add(emptylabel);
                JLabel averageLabel = new JLabel("Average: " + average);
                panel.add(averageLabel);

            getContentPane().add(panel);
            pack();
            setVisible(true);
            System.out.println("Here are the reviews");
        } else {
            JOptionPane.showMessageDialog(this,"No review and ratings record found");
            System.out.println("Failed");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
