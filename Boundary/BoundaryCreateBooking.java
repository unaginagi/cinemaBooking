package Boundary;

import Controller.ControllerCreateBooking;
import Controller.ControllerSearchTicketType;
import Controller.GetBookingListController;
import Controller.GetMovieListController;
import Controller.GetRoomListController;
import Controller.GetSessionListController;
import Controller.retrieveUserAccountControl;
import Entity.ticketType;
import Entity.userAccount;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.HashSet;
import java.util.Set;

public class BoundaryCreateBooking extends JFrame implements ActionListener {
    private JComboBox<String> movieSessionComboBox;
    private JComboBox<String> ticketTypeComboBox;
    private JTextField quantityField;
    private JTextField quantityValField;
    private JButton addButton;
    
    static ControllerCreateBooking bookingCreateCtrl = new ControllerCreateBooking();
    static GetMovieListController getMovieCtrl = new GetMovieListController();
    static GetSessionListController getSessionCtrl = new GetSessionListController();
    static retrieveUserAccountControl getUserInfoCtrl = new retrieveUserAccountControl();
    static ControllerSearchTicketType searcTicketCtrl = new ControllerSearchTicketType();
    static GetRoomListController getRoomsCtrl = new GetRoomListController();
    static GetBookingListController getBookingCtrl = new GetBookingListController();
    
    private int cUID;
    Set<String> addedItems = new HashSet<>();
    
    private final ArrayList<Integer> ms1 = new ArrayList<>();
    private final ArrayList<String> ms2 = new ArrayList<>();
    private final ArrayList<Integer> ms3 = new ArrayList<>();
    
    private final ArrayList<Integer> r0 = new ArrayList<>();
    private final ArrayList<String> r1 = new ArrayList<>();
    private final ArrayList<Integer> r2 = new ArrayList<>();
    private final ArrayList<String> r3 = new ArrayList<>();
           
    public BoundaryCreateBooking(int UID) {
        setTitle("Create a Booking");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        cUID = UID;
        
        // Create the JComboBox instances
        movieSessionComboBox = new JComboBox<>();
        ticketTypeComboBox = new JComboBox<>();
        
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.weightx = 1.0; // Equal horizontal weight for all buttons
        
        // Populate the JComboBox options
        populateMovieSessions();
        populateTicketType();
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Movie Session: "),gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(movieSessionComboBox,gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Ticket Type: "), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(ticketTypeComboBox, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        JLabel quantityLabel = new JLabel("Number of tickets:");
        panel.add(quantityLabel,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        quantityValField = new JTextField();
        panel.add(quantityValField,gbc);

        gbc.gridx = 0; 
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        addButton = new JButton("Make Booking");
        addButton.addActionListener((ActionEvent e) -> {
            try {
                System.out.println("Booking...");
                
                // Get the entered values
                String selectedMovieSession = (String) movieSessionComboBox.getSelectedItem();
                String selectedTicketType = (String) ticketTypeComboBox.getSelectedItem();
                
                String[] parts = selectedMovieSession.split(", ");
                String sessionTime = parts[1];
                int roomID = Integer.parseInt(parts[2]);
                
                int ticketID = getTicketID(selectedTicketType);
                
                int quantity = Integer.parseInt(quantityValField.getText());
                
                double ticketPrice = getTicketPrice(selectedTicketType);
                double price = quantity * ticketPrice;
                
                boolean result = bookingCreateCtrl.createBooking(roomID, sessionTime, cUID, ticketID, quantity, price);
                displayMsg(result);
                
                //if booking successful, allow for pre-purchase of food
                if (result == true){
                    BoundaryPrepurchaseFnBDialog dialog = new BoundaryPrepurchaseFnBDialog(this, "Do you want to prepurchase some food?");
                    boolean answer = true;
                    answer = dialog.getAnswer();
                    int cBID = getCurrentBookingID(roomID,sessionTime,cUID, ticketID, quantity);
                    if (answer) {
                        // Redirect to Pre-purchase food page
                        addprebookboundary preBookB = new addprebookboundary(cBID);
                        preBookB.setVisible(true);
                        dispose();
                    }
                }
                // Close the dialog
                dispose();
            } catch (Exception ex) {
                Logger.getLogger(BoundaryCreateBooking.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        panel.add(addButton,gbc);
        
        add(panel);
    }
    
    private void displayMsg(boolean result){
        if (result) {
                JOptionPane.showMessageDialog(this, "Transaction complete. Booking added successfully");
                System.out.println("Success!");
            } else {
                JOptionPane.showMessageDialog(this, "Transaction Failed. Booking Failed. Please try again");
                System.out.println("Failed.");
            }
    }
    
    private void populateMovieSessions(){
        try {
            System.out.println("get session timing");
            
            ArrayList<String[]> sessionArr = getSessionCtrl.executeTask();
            for (String[] session : sessionArr) {
                // Index 0 is "roomID", Index 1 is "sessionTiming", Index 2 is "MovieID"
                int sessID = Integer.parseInt(session[0]);
                ms1.add(sessID);
                 
                String sessTime = session[1];
                ms2.add(sessTime);
                
                int movID = Integer.parseInt(session[2]);
                ms3.add(movID);  
            }
            
            ArrayList<String[]> roomArr = getRoomsCtrl.executeTask();
            
            for (String[] r : roomArr) {
                int id = Integer.parseInt(r[0]);
                r0.add(id);
                
                String name = r[1];
                r1.add(name);
                
                int cap = Integer.parseInt(r[2]);
                r2.add(cap);
                
                String state = r[3];
                r3.add(state);
            }
            
            getRoomCapacity();
            
            // Get the current date and time
            LocalDateTime now = LocalDateTime.now();

            // Define the date and time format of the strings in ms2
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            
            // add into combo box if session timing is still available
            for (String dateTimeStr : ms2) {
                // Parse the string into a LocalDateTime object
                LocalDateTime dateTime = LocalDateTime.parse(dateTimeStr, formatter);

                // Compare the date and time with the current date and time
                int comparison = dateTime.compareTo(now);
                
                if (comparison > 0) {
                    // Date is in the future
                    // check if room is available 
                    System.out.println("session is in the future");
                    for (int i = 0; i < ms2.size(); i++){
                        for (int j = 0; j < r0.size();j++){
                            // check room state
                            System.out.println("checking room state ");
                            if (ms1.get(i).equals(r0.get(j)) && r3.get(j).equals("Available")){
                                // check room capacity
                                System.out.println("Checking room capacity");
                                if (r2.get(j) > 0){
                                    String item =  reformItem(dateTimeStr);
                                    System.out.println("item added: " + item);
                                    // Check for duplicates before adding
                                    if (!addedItems.contains(item)) {
                                        movieSessionComboBox.addItem(item);
                                        addedItems.add(item);
                                        System.out.println("added");
                                    }
                                }
                            }
                        }
                    } 
                } else {
                    // Date is in the past or equal to the current date
                    System.out.println("false");
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(BoundaryCreateBooking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    private void populateTicketType(){
        try {
            List<ticketType> listTicket = searcTicketCtrl.searchTicketType();

            for (ticketType ticket : listTicket) {
                String tName = ticket.getTypeName();
                ticketTypeComboBox.addItem(tName);  // Add for student age group
            }
        } catch (Exception ex) {
            Logger.getLogger(BoundaryCreateBooking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
     private void getRoomCapacity(){
        ArrayList<String[]> bookingArr = getBookingCtrl.executeTask(cUID);
        
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the date and time format of the strings in ms2
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        
        for (int i = 0; i > r0.size(); i++){
            for (String[] booking : bookingArr) {
                String bsession = booking[2];
                int broom = Integer.parseInt(booking[1]);
                int bquantity = Integer.parseInt(booking[5]);
                
                // Parse the string into a LocalDateTime object
                LocalDateTime dateTime = LocalDateTime.parse(bsession, formatter);

                // Compare the date and time with the current date and time
                int comparison = dateTime.compareTo(now);
                    
                if (broom == r0.get(i)){
                    if (comparison > 0){    // if booking is in the future
                        //r2 is capacity
                        r2.set(i, r2.get(i)-bquantity);
                    } 
                }
            }
        }
     }
    
    private String reformItem(String session){
        String item = "";
        for (int i = 0; i < ms2.size(); i++){
            if (ms2.get(i) == session){
                String mname = getMovieName(ms3.get(i));
                item = mname + ", " + session + ", " + ms1.get(i);
            }
        }
        return item;
    }
    
    private String getMovieName(int id){
        try {
            ArrayList<String[]> movieArr = getMovieCtrl.executeTask();
            
            
            for (String[] movie : movieArr) {
                
                if (movie[0].equals(String.valueOf(id))){
                    return movie[1];
                }
            }
            return "";
        } catch (Exception ex) {
            Logger.getLogger(BoundaryCreateBooking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
     }
    
    
    private int getTicketID(String m) throws Exception{
        List<ticketType> listTicket = searcTicketCtrl.searchTicketType();
        for (ticketType t : listTicket) {
            String name = t.getTypeName() ;
            if (name.equals(m)){
                return t.getid();
            }
        }
        return 0;
     }
    
    private double getTicketPrice(String m) throws Exception{
        List<ticketType> listTicket = searcTicketCtrl.searchTicketType();
        
        for (ticketType t : listTicket) {
            String name = t.getTypeName() ;
            if (name.equals(m)){
                return t.getPrice();
            }
        }
        return 0;
     }
    
    private int getCurrentBookingID(int roomID, String sessionTime, int UID, int ticketID, int quantity){
        ArrayList<String[]> bookingArr = getBookingCtrl.executeTask(cUID);
        
        for (String[] book: bookingArr){
            if (book[1].equals(String.valueOf(roomID))){
                if (book[2].equals(String.valueOf(sessionTime))){
                    if (book[3].equals(String.valueOf(UID))){
                        if (book[4].equals(String.valueOf(ticketID))){
                            if (book[5].equals(String.valueOf(quantity)))
                                return Integer.parseInt(book[0]);
                        }
                    }
                }
            }
        }
        return 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}