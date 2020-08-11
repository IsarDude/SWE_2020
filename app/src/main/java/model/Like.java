package model;

public class Like {
    private int likedUserId;
    Match match;

    public Like(int likedUserID){
        this.likedUserId = likedUserID;
    }

    public int getLikedUserId(){
        return likedUserId;
    }

    public void checkForMatch(int userId){

    }

    public void createMatch(){
        match = new Match(likedUserId);
    }

    public Match getMatch(){
        return this.match;
    }
}
