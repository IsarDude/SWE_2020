package model;

public class Like {
    private String likedUserId;

    public Like(String likedUserID){
        this.likedUserId = likedUserID;
    }

    public String getLikedUserId(){
        return likedUserId;
    }

    public void checkForMatch(String userId){

    }

    public void createMatch(){
        Match match = new Match(likedUserId);
    }
}
