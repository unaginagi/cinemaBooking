/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.foodsales;
import java.sql.SQLException;


public class updateprebookcontroller {
	
    public updateprebookcontroller() {}

    public boolean updateprebookfood (int foodID, int quantity, double price, int fsalesID) throws SQLException, ClassNotFoundException
    {
        foodsales f1 = new foodsales ();
        boolean output = f1.updateprebookfood (foodID, quantity, price, fsalesID);
        return output;
    }
	

}