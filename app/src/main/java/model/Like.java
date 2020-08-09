package model;

public class Like {
    private String likedUserId;
    Match match;

    public Like(String likedUserID){
        this.likedUserId = likedUserID;
    }

    public String getLikedUserId(){
        return likedUserId;
    }

    public void checkForMatch(String userId){

    }

    public void createMatch(){
        match = new Match(likedUserId);
    }

    public Match getMatch(){
        return this.match;
    }
}
