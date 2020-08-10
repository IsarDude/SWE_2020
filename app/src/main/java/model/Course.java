package model;

public class Course{
    private String courseId;
    private Timeslot[] timeslots;

    public Course(String courseId, Timeslot[] timeslots){
        this.courseId = courseId;
        this.timeslots = timeslots;
    }

    public String getCourseID(){
        return this.courseId;
    }

    public void setCourseID(String courseId){
        this.courseId = courseId;
    }
}