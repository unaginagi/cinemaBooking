/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author ASUS
 */
public class GenerateReportBookingMonthlyController {
    public static String generateReportBookingMonthlyController(String selectMonthString, String selectYearString){
        return Entity.reportBooking.generateMonthlyReport(selectMonthString, selectYearString);
    }

}
