package model;

public class Dislike {

    private int otherUserID;

    public Dislike(int otherUserID){
        this.otherUserID = otherUserID;
    }

    public int getDislikedUserId(){
        return otherUserID;
    }
}
