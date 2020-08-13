package controller;

import model.Dater;

public class NewProfileController {

    Dater dater = Dater.getInstance();

    public void newUserControl(String email, String password, String firstName, int age, String gender, String infoText, String hobby){
        dater.createUser(email, password, firstName, age, gender, infoText, hobby);
    }
}
