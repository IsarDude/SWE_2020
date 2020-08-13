package controller;

import model.Dater;
import model.User;

public class CardsController {
    private Dater dater;
    private User otherUser;
    private int otherUserID;

    public CardsController() {
        dater = Dater.getInstance();
        //TODO: getOtherUser(); <- Auskommentieren hier rückgängig machen wenn DB-Anbindung funktioniert
    }

    public void like() {
        dater.like(otherUserID);
        getOtherUser();
    }

    public void dislike() {
        dater.dislike(otherUserID);
        getOtherUser();
    }

    private void getOtherUser() {
        otherUser = dater.showCard();
        otherUserID = otherUser.getUserID();
    }

    public String getOtherUserNameAndAge() {
        //TODO: Auskommentieren hier rückgängig machen wenn DB-Anbindung funktioniert
        /*String otherUserName = otherUser.getFirstName();
        int otherUserAge = otherUser.getAge();
        String cardInfo = otherUserName + ", " + otherUserAge;
        return cardInfo;*/
        return "Emily, 24";
    }

    //Hier könnte eine Methode hin um das Profilbild des otherUser zu getten

}
