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
        match = new Match(10, likedUserId);     //10 ist Plathalter, damit kein Fehler geworfen wird, Match erwartet momentan noch int matchID und int likeduseerid
    }

    public Match getMatch(){
        return this.match;
    }
}
