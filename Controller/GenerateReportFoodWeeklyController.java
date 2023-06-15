/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

/**
 *
 * @author ASUS
 */
public class GenerateReportFoodWeeklyController {
    public static String generateReportFoodWeeklyController(String startDate, String endDate){
        return Entity.reportFood.generateWeeklyReport(startDate, endDate);
    }

}
