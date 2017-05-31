package com.example.clive.studentplanner;

/**
 * Created by Clive on 29/05/2017.
 */

public class CourseDetails {

    private String courseId;
    private String courseName;
    private String courseDay;
    private String courseStartTime;
    private String courseFinishTime;

    public CourseDetails(){}

    public CourseDetails(String courseId, String courseName, String courseDay, String courseStartTime, String courseFinishTime) {

        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDay = courseDay;
        this.courseStartTime = courseStartTime;
    }

}
