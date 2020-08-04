package model;

public class Location{

    private float gps;
    private String name;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setGPS(float gps){
        this.gps = gps;
    }

    public float getGPS(){
        return this.gps;
    }
}