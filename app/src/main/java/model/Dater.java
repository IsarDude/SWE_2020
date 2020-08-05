package model;

import java.io.File;

public class Dater {

    public void showCard(){
        //return type has to be User
    }

    public void viewProfile(String userID){

    }

    public void like(){

    }

    public void dislike(){

    }

    public void showMatches(){
        //return type must be User[]
    }

    public void showMessenger(String otherUserID){
        //return type must be Message[]
    }

    public void changeUserInfo(String firstName, String lastName, String age, int gender, String email, boolean visible, String language, String infoText){

    }

    public void showMatchingFreetime(String otherUserID){

    }

    public void login(String email, String password){

    }

    public void logout(){

    }

    public void addPhoto(File photo){

    }

    public void removePhoto(String photo){

    }

    public void buildCard(/*User showUser*/){
        //User not yet implemented
    }

    public void addHobby(String name){

    }

    public void removeHobby(String hobbyID){

    }

    public void editSubject(String name, int semester){

    }

    public void connectToDatabase(){

    }

    //Probably not in final version
    public void sendTextMessage(String otherUserID, String content){}
    public void sendVoiceMessage(String otherUserID, File content){}
    public void sendVideoMessage(String otherUserID, File content){}
    public void sendPicture(String otherUserID, File content){}


}
