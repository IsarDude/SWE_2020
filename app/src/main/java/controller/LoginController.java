package controller;

import model.Dater;

public class LoginController {
    Dater dater = Dater.getInstance();

    public boolean loginControl(String email, String password){
        return dater.login(email, password);
    }
}
