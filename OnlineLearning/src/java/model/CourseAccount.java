package model;

import java.sql.Date;

public class CourseAccount {

    private Account accountId;
    private Course courseId;
    private Date enrollDate;
    private int rating;
    private int process;
    
    public CourseAccount() {
    }

    public CourseAccount(Account accountId, Course courseId, Date enrollDate, int rating, int process) {
        this.accountId = accountId;
        this.courseId = courseId;
        this.enrollDate = enrollDate;
        this.rating = rating;
        this.process = process;
    }

    public int getProcess() {
        return process;
    }

    public void setProcess(int process) {
        this.process = process;
    }

    

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}