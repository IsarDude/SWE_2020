package model;

import datingDatabase.ConnectMySql;

public class Like {
    private int otherUserID;
    private Match match;
    private ConnectMySql connectMySql;

    public Like(int otherUserID){
        this.otherUserID = otherUserID;
        connectMySql = new ConnectMySql();
    }

    public int getOtherUserID(){
        return otherUserID;
    }

    public void checkForMatch(int userId){
        if (connectMySql.checkForMatch(otherUserID, userId)) {
            createMatch();
        }
    }

    public void createMatch(){
        match = new Match(10, otherUserID);     //10 ist Plathalter, damit kein Fehler geworfen wird, Match erwartet momentan noch int matchID und int likeduseerid
    }

    public Match getMatch(){
        return this.match;
    }
}
