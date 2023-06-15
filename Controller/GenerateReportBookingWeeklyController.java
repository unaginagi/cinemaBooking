/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author ASUS
 */
public class GenerateReportBookingWeeklyController {
    public static String generateReportBookingWeeklyController(String startDate, String endDate){
        return Entity.reportBooking.generateWeeklyReport(startDate, endDate);
    }

}
