package com.example.clive.studentplanner;

/**
 * Created by Clive on 25/05/2017.
 */

public class StudentDetails {

    private String studentNumber;
    private String firstName;
    private String lastName;
    private String major;

    public StudentDetails(){}

//    public StudentDetails(String studentNumber,String firstName,String lastName,String major){
        //todo:remove
public StudentDetails(String studentNumber){
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public String getMajor() {
//        return major;
//    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public void setMajor(String major) {
//        this.major = major;
//    }
}
