package com.example.clive.studentplanner;

/**
 * Created by Clive on 29/05/2017.
 */

public class CourseDetails {

    private String courseDay;
    private String courseId;
    private int courseStartTimeHour;
    private int courseStartTimeMinute;
    private int courseFinishTimeHour;
    private int courseFinishTimeMinute;
    private String room;

    public CourseDetails(){}

    public CourseDetails(String courseId, String courseDay, String room,
                         int courseStartTimeHour, int courseStartTimeMinute,
                         int courseFinishTimeHour, int courseFinishTimeMinute) {

        this.courseId = courseId;
        this.courseDay = courseDay;
        this.courseStartTimeHour = courseStartTimeHour;
        this.courseStartTimeMinute = courseStartTimeMinute;
        this.courseFinishTimeHour = courseFinishTimeHour;
        this.courseFinishTimeMinute = courseFinishTimeMinute;
        this.room = room;
    }
    //    private String courseName;
    public  String getCourseDay() { return courseDay; }

    public String getCourseId() { return courseId; }

    public int getCourseStartTimeHour() { return courseStartTimeHour; }

    public int getCourseStartTimeMinute() { return courseStartTimeMinute; }

    public int getCourseFinishTimeHour() { return courseFinishTimeHour; }

    public int getCourseFinishTimeMinute() { return courseFinishTimeMinute; }

    public String getRoom() { return room; }

    public void   setCourseDay(String courseDay) { this.courseDay = courseDay; }

    public void   setCourseId(String courseId) { this.courseId = courseId; }

    public void   setCourseStartTimeHour(int courseStartTime) { this.courseStartTimeHour = courseStartTime; }

    public void   setCourseStartTimeMinute(int courseStartTime) { this.courseStartTimeMinute = courseStartTime; }

    public void   setCourseFinishTimeHour(int courseFinishTime) { this.courseFinishTimeHour = courseFinishTime; }

    public void   setCourseFinishTimeMinute(int courseFinishTime) { this.courseFinishTimeMinute = courseFinishTime; }

    public void   setRoom(String room) { this.room = room; }
}
