package model;

public class Subject{

    private int semester;
    private String name;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setSemester(int semester){
        this.semester = semester;
    }

    public int getSemester(){
        return this.semester;
    }
}