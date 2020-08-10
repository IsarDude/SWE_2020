package model;

import java.sql.Timestamp;

public class Match {

    private Timestamp timestamp;
    private String matchId;

    public Match(String matchId){
        timestamp = new Timestamp(System.currentTimeMillis());
        this.matchId = matchId;
    }

    public void sendMessage(int messageType, String content){

    }

    public String[] lookForCommonFreeTime(){
        return null;
    }

    public void blockUser(){

    }
}
