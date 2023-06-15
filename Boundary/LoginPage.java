package Boundary;

import Controller.LoginController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import javax.imageio.ImageIO;

public class LoginPage extends JFrame implements ActionListener {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton createAccountButton;

    public static boolean isLoggedin = false;

        public LoginPage() {
        setTitle("UNAGI Cinema");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create the title label
        JLabel titleLabel = new JLabel("Welcome to UNAGI Cinema");
        titleLabel.setFont(new Font("Century Gothic", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Create the image panel
        JPanel imagePanel = new JPanel();
        imagePanel.setPreferredSize(new Dimension(200, 200));
        mainPanel.add(imagePanel, BorderLayout.CENTER);

        // Load and display the image
        try {
            URL imageURL = new URL("https://i.imgur.com/92AOrL3.png");
            BufferedImage image = ImageIO.read(imageURL);
            JLabel imageLabel = new JLabel(new ImageIcon(image));
            imagePanel.add(imageLabel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create the input panel
        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        mainPanel.add(inputPanel, BorderLayout.SOUTH);

        // Username field
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);

        // Password field
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);

        // Login button
        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        inputPanel.add(loginButton);

        // Create account button
        createAccountButton = new JButton("Create Account");
        createAccountButton.addActionListener(this);
        inputPanel.add(createAccountButton);

        add(mainPanel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            int loggedInUserProfileID= -1;
            int loggedInUserID = -1;
            // Authentication Result
            //int[] loggedInUserArray = LoginPageLogin(username, password);
            //loggedInUserArray[1]
            if (LoginPageLogin(username, password)[1]>0) {
                JOptionPane.showMessageDialog(this, "Login successful!");
                dispose();
                //WHAT IS SEEN BASED ON WHO LOGS IN
                if (LoginPageLogin(username, password)[1]==1){
                    // USER: USER ADMIN
                    java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new DashboardUserAdmin().setVisible(true);
                    }
                    });
                    
                } else if (LoginPageLogin(username, password)[1]==2){
                    // USER: CINEMA OWNER - view report
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new DashboardCinemaOwner().setVisible(true);
                        }
                    });
                } else if (LoginPageLogin(username, password)[1]==3){
                    // USER: CINEMA MANAGER - manage food drinks cinema room etc
                        java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            new DashboardCinemaManager().setVisible(true);
                        }
                        });
                }else if (LoginPageLogin(username, password)[1]==4){
                    // USER: CUSTOMER - book ticket
                    java.awt.EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        new DashboardCustomer(LoginPageLogin(username, password)[0]).setVisible(true);
                    }
                    });
                } else {
                    // USER: undefined
                    JOptionPane.showMessageDialog(this, 
                            "You do not have permissions. "
                                    + "Please contact customer service at "
                                    + "86421356", "Error", 
                                    JOptionPane.ERROR_MESSAGE);
                }
                
                /*
                //dashboard (old)
                SwingUtilities.invokeLater(() -> {
                    Dashboard dashboardFrame = new Dashboard();
                    dashboardFrame.setVisible(true);
                });
                */
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == createAccountButton) {
            dispose();
            //create new account
            SwingUtilities.invokeLater(() -> {
                createCustomerAccountGUI createAccountPage = new createCustomerAccountGUI();
                createAccountPage.setVisible(true);
            });
        }
    }

    private int[] LoginPageLogin(String username, String password) {
        int[] loggedInUserProfileID = { -1, -1 };
        try {
            loggedInUserProfileID = LoginController.login(username,password);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database Error", "Alert", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Unknown Error", "Alert", JOptionPane.WARNING_MESSAGE);      
        }
        return loggedInUserProfileID;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}