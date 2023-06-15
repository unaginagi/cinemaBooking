package Controller;

import Entity.foodItem;
import java.util.List;

public class searchfoodcontroller {

    public searchfoodcontroller() {
    }

    public List<foodItem> searchfoodItem(String name){
        foodItem f1 = new foodItem();
        List<foodItem> output = f1.searchfood(name);
        return output;
    }

}
