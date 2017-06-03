package com.example.clive.studentplanner;

/**
 * Created by Clive on 29/05/2017.
 */

public class CourseDetails {

    private String courseDay;
    private String courseId;
    private String courseStartTime;
    private String courseFinishTime;
    private String room;

    public CourseDetails(){}

    public CourseDetails(String courseId, String courseDay, String courseStartTime, String courseFinishTime, String room) {

        this.courseId = courseId;
        this.courseDay = courseDay;
        this.courseStartTime = courseStartTime;
        this.courseFinishTime = courseFinishTime;
        this.room = room;
    }
    //    private String courseName;
    public  String getCourseDay() { return courseDay; }

    public String getCourseId() { return courseId; }

    public String getCourseStartTime() { return courseStartTime; }

    public String getCourseFinishTime() { return courseFinishTime; }

    public String getRoom() { return room; }

    public void   setCourseDay(String courseDay) { this.courseDay = courseDay; }

    public void   setCourseId(String courseId) { this.courseId = courseId; }

    public void   setCourseStartTime(String courseStartTime) { this.courseStartTime = courseStartTime; }

    public void   setCourseFinishTime(String courseFinishTime) { this.courseFinishTime = courseFinishTime; }

    public void   setRoom(String room) { this.room = room; }
}
