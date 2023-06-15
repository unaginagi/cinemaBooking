
package Entity;

import java.sql.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

public class reportFood {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/cinemabooking";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "password";

    public static String generateDailyReport(String date) {
    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Prepare and execute the SQL query
        String query = "SELECT f.name AS FoodName, SUM(fs.quantity) AS TotalQuantity, SUM(fs.price * fs.quantity) AS TotalEarnings " +
                "FROM booking b " +
                "JOIN food_sales fs ON b.bookingID = fs.bookingID " +
                "JOIN food_items f ON fs.foodID = f.id " +
                "WHERE DATE(b.SessionTiming) = ? " +
                "GROUP BY f.name " +
                "ORDER BY TotalEarnings DESC";
        PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        statement.setString(1, date);
        ResultSet resultSet = statement.executeQuery();

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Daily report: ").append(date).append("\n");

        while (resultSet.next()) {
            String foodName = resultSet.getString("FoodName");
            int totalQuantity = resultSet.getInt("TotalQuantity");
            double totalEarnings = resultSet.getDouble("TotalEarnings");
            String formattedTotalEarnings = new DecimalFormat("0.00").format(totalEarnings); // Format to 2 decimal places

            reportBuilder.append("Food: ").append(foodName).append("\n");
            reportBuilder.append("Total Quantity Sold: ").append(totalQuantity).append("\n");
            reportBuilder.append("Total Earnings: $").append(formattedTotalEarnings).append("\n\n");
        }

        // Retrieve the total sales for the entire day
        resultSet.last();
        int rowCount = resultSet.getRow();
        if (rowCount > 0) {
            resultSet.beforeFirst();
            double totalSales = 0;
            while (resultSet.next()) {
                totalSales += resultSet.getDouble("TotalEarnings");
            }
            String formattedTotalSales = new DecimalFormat("0.00").format(totalSales); // Format to 2 decimal places
            reportBuilder.append("Total Sales for the Day: $").append(formattedTotalSales);
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
        String query = "SELECT f.name AS FoodName, SUM(fs.quantity) AS TotalQuantity, SUM(fs.price * fs.quantity) AS TotalEarnings " +
                "FROM booking b " +
                "JOIN food_sales fs ON b.bookingID = fs.bookingID " +
                "JOIN food_items f ON fs.foodID = f.id " +
                "WHERE DATE(b.SessionTiming) BETWEEN ? AND ? " +
                "GROUP BY f.name " +
                "ORDER BY TotalEarnings DESC";
        PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        statement.setString(1, startDate);
        statement.setString(2, endDate);
        ResultSet resultSet = statement.executeQuery();

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Weekly report: ").append(startDate).append(" to ").append(endDate).append("\n");

        while (resultSet.next()) {
            String foodName = resultSet.getString("FoodName");
            int totalQuantity = resultSet.getInt("TotalQuantity");
            double totalEarnings = resultSet.getDouble("TotalEarnings");
            String formattedTotalEarnings = new DecimalFormat("0.00").format(totalEarnings); // Format to 2 decimal places

            reportBuilder.append("Food: ").append(foodName).append("\n");
            reportBuilder.append("Total Quantity Sold: ").append(totalQuantity).append("\n");
            reportBuilder.append("Total Earnings: $").append(formattedTotalEarnings).append("\n\n");
        }

        // Retrieve the total sales for the entire week
        resultSet.last();
        int rowCount = resultSet.getRow();
        if (rowCount > 0) {
            resultSet.beforeFirst();
            double totalSales = 0;
            while (resultSet.next()) {
                totalSales += resultSet.getDouble("TotalEarnings");
            }
            String formattedTotalSales = new DecimalFormat("0.00").format(totalSales); // Format to 2 decimal places
            reportBuilder.append("Total Sales for the Week: $").append(formattedTotalSales);
        }

        return reportBuilder.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static String generateMonthlyReport(String month, String year) {
    try (Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD)) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        // Prepare and execute the SQL query
        String query = "SELECT f.name AS FoodName, SUM(fs.quantity) AS TotalQuantity, SUM(fs.price * fs.quantity) AS TotalEarnings " +
                "FROM booking b " +
                "JOIN food_sales fs ON b.bookingID = fs.bookingID " +
                "JOIN food_items f ON fs.foodID = f.id " +
                "WHERE MONTH(b.SessionTiming) = ? AND YEAR(b.SessionTiming) = ? " +
                "GROUP BY f.name " +
                "ORDER BY TotalEarnings DESC";
        PreparedStatement statement = connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        statement.setString(1, month);
        statement.setString(2, year);
        ResultSet resultSet = statement.executeQuery();

        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Monthly report: ").append(month).append(" ").append(year).append("\n");

        while (resultSet.next()) {
            String foodName = resultSet.getString("FoodName");
            int totalQuantity = resultSet.getInt("TotalQuantity");
            double totalEarnings = resultSet.getDouble("TotalEarnings");
            String formattedTotalEarnings = new DecimalFormat("0.00").format(totalEarnings); // Format to 2 decimal places

            reportBuilder.append("Food: ").append(foodName).append("\n");
            reportBuilder.append("Total Quantity Sold: ").append(totalQuantity).append("\n");
            reportBuilder.append("Total Earnings: $").append(formattedTotalEarnings).append("\n\n");
        }

        // Retrieve the total sales for the entire month
        resultSet.last();
        int rowCount = resultSet.getRow();
        if (rowCount > 0) {
            resultSet.beforeFirst();
            double totalSales = 0;
            while (resultSet.next()) {
                totalSales += resultSet.getDouble("TotalEarnings");
            }
            String formattedTotalSales = new DecimalFormat("0.00").format(totalSales); // Format to 2 decimal places
            reportBuilder.append("Total Sales for the Month: $").append(formattedTotalSales);
        }

        return reportBuilder.toString();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}


