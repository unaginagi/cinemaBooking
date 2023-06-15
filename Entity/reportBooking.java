package Entity;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class reportBooking {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cinemabooking";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "password";
    
    public String report;

    public reportBooking(){
        String report  = "";
    }
    
    public String getReport(){
        return report;
    }
    
    public void setReport(String report){
        this.report = report;
    }

    public static String generateDailyReport(String date) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Prepare and execute the SQL query
            String query = "SELECT m.Name, SUM(b.price) AS TotalSales " +
                    "FROM booking b " +
                    "JOIN MovieSession ms ON b.roomID = ms.RoomID AND b.SessionTiming = ms.SessionTiming " +
                    "JOIN Movie m ON ms.MovieID = m.ID " +
                    "WHERE DATE(b.SessionTiming) = ? " +
                    "GROUP BY m.Name";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, date);
            ResultSet resultSet = statement.executeQuery();

            StringBuilder reportBuilder = new StringBuilder();
            reportBuilder.append("Daily report: ").append(date).append("\n");
            while (resultSet.next()) {
                String movieName = resultSet.getString("Name");
                double totalSales = resultSet.getDouble("TotalSales");
                System.out.print("TotalSales double = " + totalSales);
                String formattedTotalSales = new DecimalFormat("0.00").format(totalSales); // Format to 2 decimal places
                System.out.print("formattedTotalSales string = " + formattedTotalSales);
                reportBuilder.append("Movie: ").append(movieName).append("\n");
                reportBuilder.append("Total Sales: $").append(formattedTotalSales).append("\n\n");
            }

            return reportBuilder.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String generateWeeklyReport(String startDate, String endDate) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            // Prepare and execute the SQL query
            String query = "SELECT m.Name, SUM(b.price) AS TotalSales " +
                    "FROM booking b " +
                    "JOIN MovieSession ms ON b.roomID = ms.RoomID AND b.SessionTiming = ms.SessionTiming " +
                    "JOIN Movie m ON ms.MovieID = m.ID " +
                    "WHERE DATE(b.SessionTiming) >= ? AND DATE(b.SessionTiming) <= ? " +
                    "GROUP BY m.Name";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, startDate);
            statement.setString(2, endDate);
            ResultSet resultSet = statement.executeQuery();

            StringBuilder reportBuilder = new StringBuilder();
            reportBuilder.append("Weekly report: ").append(startDate).append(" to ").append(endDate).append("\n");
            while (resultSet.next()) {
                String movieName = resultSet.getString("Name");
                double totalSales = resultSet.getDouble("TotalSales");
                String formattedTotalSales = new DecimalFormat("0.00").format(totalSales); // Format to 2 decimal places
                reportBuilder.append("Movie: ").append(movieName).append("\n");
                reportBuilder.append("Total Sales: $").append(formattedTotalSales).append("\n\n");
                System.out.println(reportBuilder);
            }

            return reportBuilder.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
     public static String generateMonthlyReport(String month, String year) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
            // Prepare and execute the SQL query
            String query = "SELECT m.Name, SUM(b.price) AS TotalSales " +
                    "FROM booking b " +
                    "JOIN MovieSession ms ON b.roomID = ms.RoomID AND b.SessionTiming = ms.SessionTiming " +
                    "JOIN Movie m ON ms.MovieID = m.ID " +
                    "WHERE MONTH(b.SessionTiming) = ? AND YEAR(b.SessionTiming) = ? " +
                    "GROUP BY m.Name";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, month);
            statement.setString(2, year);
            ResultSet resultSet = statement.executeQuery();

            StringBuilder reportBuilder = new StringBuilder();
            reportBuilder.append("Monthly report: ").append(month).append(" ").append(year).append("\n");
            while (resultSet.next()) {
                String movieName = resultSet.getString("Name");
                double totalSales = resultSet.getDouble("TotalSales");
                String formattedTotalSales = new DecimalFormat("0.00").format(totalSales); // Format to 2 decimal places
                reportBuilder.append("Movie: ").append(movieName).append("\n");
                reportBuilder.append("Total Sales: $").append(formattedTotalSales).append("\n\n");
                System.out.println(reportBuilder);
            }

            return reportBuilder.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    
}