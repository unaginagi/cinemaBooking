package Controller;

import Entity.foodsales;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class searchprebookcontroller {

    public searchprebookcontroller() {
    }

    public ArrayList<String[]> searchprebookfood(int UID){
        try {
            foodsales f1 = new foodsales();
            return f1.searchprebookfood(UID);
        } catch (SQLException ex) {
            Logger.getLogger(searchprebookcontroller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(searchprebookcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}