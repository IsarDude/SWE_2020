package model;

import java.io.File;

public class Dater {
    User user;

    public User showCard(){
        return user;
    }

    public void viewProfile(String userID){

    }

    public void like(String otherUserId){

    }

    public void dislike(){

    }

    public void showMatches(){
        //return type must be User[]
    }

    public void showMessenger(String otherUserID){
        //return type must be Message[]
    }

    public void changeUserInfo(String firstName, String lastName, int age, String gender, String email, boolean visible, String language, String infoText){
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setGender(gender);
        user.setEmail(email);
        user.setVisible(visible);
        user.setLanguage(language);
        user.setInfoText(infoText);
    }

    public void showMatchingFreetime(String otherUserID){

    }

    public void login(String email, String password){
        if(true/*database contains email*/){
            if(true/*password matches email found in database*/){
                //Login successful
                //set User as in database
            }else{
                //login failed
            }
        }
    }

    public void logout(){
        user=null;
    }

    public void addPhoto(File photo){
        //user.addPhoto(photo);
    }

    public void removePhoto(String photo){
        user.removePhoto(photo);
    }

    public void buildCard(/*User showUser*/){
        //User not yet implemented
    }

    public void addHobby(String name){
        user.addHobby(name);
    }

    public void removeHobby(String hobbyID){
        user.removeHobby(hobbyID);
    }

    public void editSubject(String name, int semester){
        user.changeSubject(semester, name);
    }

    public void connectToDatabase(){

    }

    //Probably not in final version
    public void sendTextMessage(String otherUserID, String content){}
    public void sendVoiceMessage(String otherUserID, File content){}
    public void sendVideoMessage(String otherUserID, File content){}
    public void sendPicture(String otherUserID, File content){}


}
