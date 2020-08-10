package model;

public class Dislike {

    private String dislikedUserId;

    public Dislike(String dislikedUserId){
        this.dislikedUserId = dislikedUserId;
    }

    public String getDislikedUserId(){
        return dislikedUserId;
    }
}
