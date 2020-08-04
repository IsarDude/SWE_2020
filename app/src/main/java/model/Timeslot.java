package model;

public class Timeslot{

    private int startTime;
    private int endTime;
    private String day;

    public void setDay(String day){
        this.day = day;
    }

    public String getDay(){
        return this.day;
    }

    public void setStartTime(int startTime){
        this.startTime = startTime;
    }

    public int getStartTime(){
        return this.startTime;
    }

    public void setEndTime(int endTime){
        this.endTime = endTime;
    }

    public int getEndTime(){
        return this.endTime;
    }
}