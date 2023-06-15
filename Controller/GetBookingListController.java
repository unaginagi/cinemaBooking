/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.booking;
import java.sql.SQLException;
import java.util.ArrayList;

public class GetBookingListController {
    
    
    private final booking entity = new booking();
	
	public ArrayList<String[]> executeTask(int ID){
		return entity.getBookingList(ID);
	}
}
