package controller;

import model.Dater;
import model.User;

public class EditProfileController {
    private Dater dater = Dater.getInstance();

    public void saveProfileChanges(String newFirstName, String newLastName, String newAge, String newGender, String newLanguage, String newInfoText, String newHobby, String newEmail, boolean newVisibility) {
        User user = dater.getCurrentUser();

        user.setFirstName(newFirstName);
        user.setLastName(newLastName);
        user.setAge(Integer.parseInt(newAge));
        user.setGender(newGender);
        user.setLanguage(newLanguage);
        user.setInfoText(newInfoText);
        user.addHobby(newHobby);
        user.setEmail(newEmail);
        user.setVisible(newVisibility);

        //TODO: Wechsle zurück auf das Profile Fragment

    }
}
