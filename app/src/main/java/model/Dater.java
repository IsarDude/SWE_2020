package model;

import java.io.File;

public class Dater {
    private static Dater instance;
    User user;
    SinglesPool singlesPool;

    private Dater(){

    }

    public static Dater getInstance(){
        if (Dater.instance == null) {
            Dater.instance = new Dater ();
        }
        return Dater.instance;
    }

    private static void setInstance(Dater dater){
        if(Dater.instance!=null){
            Dater.instance = null;
            Dater.instance = dater;
        }

    }

    public void createUser(String email, String password, String firstName, String lastName, int age, String gender, String language, String infoText, String hobby){
        user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAge(age);
        user.setGender(gender);
        user.setLanguage(language);
        user.setInfoText(infoText);
        user.addHobby(hobby);
    }

    public User getCurrentUser(){
        return user;
    }

    public User showCard(){
        User randomUser = singlesPool.getRandomUser();
        return randomUser;
    }

    public void viewProfile(int userID){

    }

    public void like(int otherUserId){
        user.createLike(otherUserId);
    }

    public void dislike(String dislikedUserID){
        user.createDislike(dislikedUserID);
    }

    public void showMatches(){
        //return type must be User[]
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



    public boolean login(String email, String password){
        if(true/*database contains email*/){
            if(true/*password matches email found in database*/){
                //Login successful
                //set User as in database
                //create SinglesPool
                return true;
            }else{
                //login failed
                return false;
            }
        }else{
            return false;
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
    public void showMessenger(String otherUserID){
        //return type must be Message[]
    }
    public void showMatchingFreetime(String otherUserID){

    }


}
