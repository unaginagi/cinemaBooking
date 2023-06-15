package Entity;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userProfile {
    private int profileID;
    private String description;
    
    public userProfile(int profileID, String description){
        this.profileID = profileID;
        this.description = description;
    }
    
    // Getter and Setter methods

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    // Database connection properties
    public static String DB_URL = "jdbc:mysql://localhost:3306/cinemabooking?zeroDateTimeBehavior=CONVERT_TO_NULL";
    public static String DB_USER = "root";
    public static String DB_PASSWORD = "password";
    
    public static boolean addUserProfile(String description) throws SQLException, Exception, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Retrieve the next available profile ID from the database
            PreparedStatement idStmt = myCon.prepareStatement("SELECT MAX(profileID) FROM userProfile");
            ResultSet idResultSet = idStmt.executeQuery();
            int nextProfileID = 1;
            if (idResultSet.next()) {
                nextProfileID = idResultSet.getInt(1) + 1;
            }

            // Insert the new profile into the database with the next available ID
            PreparedStatement insertStmt = myCon.prepareStatement("INSERT INTO userProfile (profileID, description) VALUES (?, ?)");
            insertStmt.setInt(1, nextProfileID);
            insertStmt.setString(2, description);

            int rowsAffected = insertStmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
}

    
    public static userProfile getUserProfile(int profileID) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);  
            
            PreparedStatement myStmt = myCon.prepareStatement("SELECT * FROM userProfile WHERE profileID = ?");
            myStmt.setInt(1, profileID);
            
            ResultSet rset = myStmt.executeQuery();
            if (rset.next()) {
                int profileID1 = rset.getInt("profileID");
                String description1 = rset.getString("description");
                
                userProfile output = new userProfile(profileID1, description1);
                return output;
            } else {
                return null; // Profile not found
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }
    
    public static List<userProfile> getAllUserProfiles() throws SQLException, ClassNotFoundException {
        List<userProfile> profiles = new ArrayList<>();
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);  
            
            Statement myStmt = myCon.createStatement();
            ResultSet rset = myStmt.executeQuery("SELECT * FROM userProfile");
            
            while (rset.next()) {
                int profileID = rset.getInt("profileID");
                String description = rset.getString("description");
                
                userProfile profile = new userProfile(profileID, description);
                profiles.add(profile);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        
        return profiles;
    }
    
    public static boolean deleteUserProfile(String description) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            
            PreparedStatement myStmt = myCon.prepareStatement("DELETE FROM userProfile WHERE description = ?");
            myStmt.setString(1, description);
            
            int rowsAffected = myStmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }
    
     public static boolean updateUserProfileDescription(String oldDescription, String newDescription) throws SQLException, ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            PreparedStatement myStmt = myCon.prepareStatement("UPDATE userProfile SET description = ? WHERE description = ?");
            myStmt.setString(1, newDescription);
            myStmt.setString(2, oldDescription);

            int rowsAffected = myStmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return false;
        }
    }
     
public static List<userProfile> searchUserProfile(String searchDescription) throws SQLException, ClassNotFoundException {
    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        List<userProfile> searchedProfiles = new ArrayList<>();
        Connection myCon = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        PreparedStatement myStmt = myCon.prepareStatement("SELECT * FROM userProfile WHERE description LIKE ?");
        myStmt.setString(1, "%" + searchDescription + "%");

        ResultSet rset = myStmt.executeQuery();
        while (rset.next()) {
            int profileID = rset.getInt("profileID");
            String description = rset.getString("description");

            userProfile profile = new userProfile(profileID, description);
            searchedProfiles.add(profile);
        }
        
        return searchedProfiles;
    } catch (SQLException e) {
        e.printStackTrace(System.out);
        return null;
    }
}

    
}

/*package JeromePackage;

import java.sql.*;
import java.util.*;

public class userProfile {
    private int profileID;
    private String description;
    
    public userProfile(int profileID, String description){
        this.profileID = profileID;
        this.description = description;
    }
    
    //get set methods
    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
    public static String DB_URL = "jdbc:mysql://localhost:3306/cinemabooking?zeroDateTimeBehavior=CONVERT_TO_NULL";
    public static String DB_USER = "admin";
    public static String DB_PASSWORD = "password";
    
    public static boolean addUserProfile (String description) 
                                throws SQLException, Exception, ClassNotFoundException
    {
        try
        {
            Class.forName ("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (DB_URL, DB_USER, DB_PASSWORD);
    
            PreparedStatement myStmt; 
            myStmt = myCon.prepareStatement("INSERT INTO userProfile (description) "
                                            + "values ('" + description + "')");
            int rset = myStmt.executeUpdate();
	    return true;
        }
        catch (SQLException e )
        {
            e.printStackTrace(System.out);
            return false;
        }
    }
    
      public static userProfile getUserProfile (int profileID) throws SQLException, ClassNotFoundException
    {
        try
        {
            Class.forName ("com.mysql.cj.jdbc.Driver");
            Connection myCon = DriverManager.getConnection (DB_URL, DB_USER, DB_PASSWORD);  
            
            PreparedStatement myStmt; 
            myStmt = myCon.prepareStatement("Select * from userprofile where profileID = " + profileID);
            ResultSet rset = myStmt.executeQuery();
            String description1 ="";
            int profileID1 = 0;
            boolean suspended2 = false;
            
            if (rset.next()) 
            {
                profileID1 = rset.getInt("profileID");
                description1 = rset.getString("description");
                
            }

            userProfile output = new userProfile (profileID1, description1);
            if (profileID==0)
            {
                return null;
            }
            return output;
        }
        catch (SQLException e )
        {
            e.printStackTrace(System.out);
            return null;
        }
    }
}


*/