package com.scheduler.wgu_scheduler_app.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

/**
 *
 */
@Entity(tableName = "course_tbl",
        foreignKeys = @ForeignKey(entity = TermEntity.class,
                                  parentColumns = "termId",
                                  childColumns = "termId",
                                  onDelete = ForeignKey.CASCADE))
public class CourseEntity {
    @PrimaryKey(autoGenerate = true)
    private int courseId;
    private int termId;

    private String courseTitle;
    private String courseStartDate;
    private String courseEndDate;
    private String courseStatus;
    private String courseInstructorNames;
    private String courseInstructorEmailAddresses;
    private String courseInstructorPhoneNumbers;

    public CourseEntity(int termId, String courseTitle, String courseStartDate, String courseEndDate, String courseStatus, String courseInstructorNames,
            String courseInstructorEmailAddresses,
            String courseInstructorPhoneNumbers) {
        this.termId = termId;
        this.courseTitle = courseTitle;
        this.courseStartDate = courseStartDate;
        this.courseEndDate = courseEndDate;
        this.courseStatus = courseStatus;
        this.courseInstructorNames = courseInstructorNames;
        this.courseInstructorEmailAddresses = courseInstructorEmailAddresses;
        this.courseInstructorPhoneNumbers = courseInstructorPhoneNumbers;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseStartDate() {
        return courseStartDate;
    }

    public void setCourseStartDate(String courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    public String getCourseEndDate() {
        return courseEndDate;
    }

    public void setCourseEndDate(String courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    public String getCourseStatus() {
        return courseStatus;
    }

    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    public String getCourseInstructorNames() {
        return courseInstructorNames;
    }

    public void setCourseInstructorNames(String courseInstructorNames) {
        this.courseInstructorNames = courseInstructorNames;
    }

    public String getCourseInstructorEmailAddresses() {
        return courseInstructorEmailAddresses;
    }

    public void setCourseInstructorEmailAddresses(String courseInstructorEmailAddresses) {
        this.courseInstructorEmailAddresses = courseInstructorEmailAddresses;
    }

    public String getCourseInstructorPhoneNumbers() {
        return courseInstructorPhoneNumbers;
    }

    public void setCourseInstructorPhoneNumbers(String courseInstructorPhoneNumbers) {
        this.courseInstructorPhoneNumbers = courseInstructorPhoneNumbers;
    }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseId=" + courseId +
                ", termId=" + termId +
                ", courseTitle='" + courseTitle + '\'' +
                ", courseStartDate='" + courseStartDate + '\'' +
                ", courseEndDate='" + courseEndDate + '\'' +
                ", courseStatus='" + courseStatus + '\'' +
                '}';
    }
}
