package com.scheduler.wgu_scheduler_app.db.entity;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.scheduler.wgu_scheduler_app.ui.course.CourseListFragment;

/**
 *
 */
@Entity(tableName = "assessment_tbl",
        foreignKeys = @ForeignKey(
                entity = CourseEntity.class,
                parentColumns = "courseId",
                childColumns = "courseId",
                onDelete = ForeignKey.CASCADE
        ))
public class AssessmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int assessmentId;
    private int courseId;
    private String assessmentName;
    private String assessmentStartDate;
    private String assessmentEndDate;
    private String assessmentType;

    public AssessmentEntity(int courseId, String assessmentName, String assessmentStartDate, String assessmentEndDate, String assessmentType) {
        this.courseId = courseId;
        this.assessmentName = assessmentName;
        this.assessmentStartDate = assessmentStartDate;
        this.assessmentEndDate = assessmentEndDate;
        this.assessmentType = assessmentType;
    }

    public int getAssessmentId() {
        return assessmentId;
    }

    public void setAssessmentId(int assessmentId) {
        this.assessmentId = assessmentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    public String getAssessmentStartDate() {
        return assessmentStartDate;
    }

    public void setAssessmentStartDate(String assessmentStartDate) {
        this.assessmentStartDate = assessmentStartDate;
    }

    public String getAssessmentEndDate() {
        return assessmentEndDate;
    }

    public void setAssessmentEndDate(String assessmentEndDate) {
        this.assessmentEndDate = assessmentEndDate;
    }

    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    @Override
    public String toString() {
        return "AssessmentEntity{" +
                "assessmentId=" + assessmentId +
                ", courseId=" + courseId +
                ", assessmentName='" + assessmentName + '\'' +
                ", assessmentDate='" + assessmentStartDate + '\'' +
                ", assessmentType='" + assessmentType + '\'' +
                '}';
    }
}
