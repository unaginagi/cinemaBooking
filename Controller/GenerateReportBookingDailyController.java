/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author ASUS
 */
public class GenerateReportBookingDailyController {
    public static String generateReportBookingDailyController(String selectedDate){
        return Entity.reportBooking.generateDailyReport(selectedDate);
    }

}
