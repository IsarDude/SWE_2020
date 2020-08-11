package model;

import java.sql.Timestamp;

public class Match {

    private Timestamp timestamp;
    private int matchId;
    private int likedUserID;

    public Match(int matchId, int aLikedUserID){
        timestamp = new Timestamp(System.currentTimeMillis());
        this.matchId = matchId;
        this.likedUserID=aLikedUserID;
    }

    public void sendMessage(int messageType, String content){

    }

    public String[] lookForCommonFreeTime(){
        return null;
    }

    public void blockUser(){

    }
}
