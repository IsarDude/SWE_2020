package controller;

import model.Dater;
import model.User;

public class ProfileController {
    private Dater dater;
    private User user;

    public ProfileController() {
        dater = Dater.getInstance();
        user = dater.getCurrentUser();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getAge() {
        return Integer.toString(user.getAge());
    }

    public String getGender() {
        return user.getGender();
    }

    public String getInfoText() {
        return user.getInfoText();
    }

    //Hier wird das zuletzt hinzugefügte Hobby zurückgegeben. Muss geändert werden, jenachdem wie wir Hobbies Anzeigen/Ändern/Hinzufügen wollen.
    public String getHobby() {
        return user.getHobbies().getLast().getName();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public boolean getVisibility() {
        return user.isVisible();
    }
}
