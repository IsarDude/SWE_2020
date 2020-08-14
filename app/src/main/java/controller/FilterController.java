package controller;

import model.Dater;

public class FilterController {
    Dater dater = Dater.getInstance();

    public void filterControl(int maxRange, int minAge,int maxAge,String gender){
        dater.getCurrentUser().changeFilter(maxRange, minAge, maxAge, gender);
        //TODO Filter speichernf
    }
}

