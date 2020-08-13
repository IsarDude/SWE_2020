package controller;

import model.Dater;

public class MainActivityController {
    Dater dater = Dater.getInstance();

    public void logoutControl(){
        dater.logout();
    }
}