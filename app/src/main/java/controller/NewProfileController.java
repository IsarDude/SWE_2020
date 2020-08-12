package controller;

import model.Dater;

public class NewProfileController {

    Dater dater = Dater.getInstance();

    public void newUserControl(String email, String password, String firstName, String lastName, int age, String gender, String language, String infoText, String hobby){
        dater.createUser(email, password, firstName, lastName, age, gender, language, infoText, hobby);
    }
}
