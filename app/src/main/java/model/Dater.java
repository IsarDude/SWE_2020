package model;

import android.util.Log;

import java.io.File;

import datingDatabase.ConnectMySql;

public class Dater {
    private static Dater instance;
    User user;
    SinglesPool singlesPool;
    ConnectMySql connectMySql;

    private Dater(){
        connectToDatabase();
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

    public void createUser(String email, String password, String firstName, int age, String gender, String infoText, String hobby){
        user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setAge(age);
        user.setGender(gender);
        user.setInfoText(infoText);
        user.addHobby(hobby);
        user.setVisible(true);

        connectMySql.addUser(email, gender, gender, firstName, age, password);
    }

    public User getCurrentUser(){
        return user;
    }

    public User showCard(){
        if(singlesPool==null){
            singlesPool = new SinglesPool(connectMySql.getUserForPool(user.getFilter().getMaxAge(),user.getFilter().getMinAge(), user.getFilter().getGenderPreferences(), user.getGender()));
        }
        return singlesPool.getRandomUser();
    }

    public void viewProfile(int userID){

    }

    public void like(int otherUserId){
        user.createLike(otherUserId);
        connectMySql.addLike(otherUserId, user.getUserID());
    }

    public void dislike(int otherUserID){
        user.createDislike(otherUserID);
        //TODO DB entry
    }

    public void showMatches(){
        //return type must be User[]
    }



    public void changeUserInfo(String firstName,int age, String gender, String email, boolean visible,String hobby, String infoText){
        user.setFirstName(firstName);
        user.setAge(age);
        user.setGender(gender);
        user.setEmail(email);
        user.setVisible(visible);
        user.setInfoText(infoText);
        user.addHobby(hobby);
    }

    public boolean login(String email, String password){
        return true;
        /*
        user = connectMySql.getCurrentUser(email, password);
        return user != null;*/
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
        //connectMySql.addHobby(user.getHobbies(), user.getUserID());
    }

    public void removeHobby(String hobbyID){
        user.removeHobby(hobbyID);
        //  connectMySql.removeHobby(user.getHobbies(), user.getUserID());
    }

    public void editSubject(String name, int semester){
        user.changeSubject(semester, name);
        connectMySql.changeSubject(user.getSubject(), user.getUserID());
    }

    public void connectToDatabase(){
        connectMySql = new ConnectMySql();
        connectMySql.execute();
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
