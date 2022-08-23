package com.scheduler.wgu_scheduler_app.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *
 */
@Entity(tableName = "assessment_tbl")
public class AssessmentEntity {
    @PrimaryKey(autoGenerate = true)
    private int assessmentId;
    private int courseId;
    private String assessmentName;
    private String assessmentDate;
    private String assessmentType;

    public AssessmentEntity(int courseId, String assessmentName, String assessmentDate, String assessmentType) {
        this.courseId = courseId;
        this.assessmentName = assessmentName;
        this.assessmentDate = assessmentDate;
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

    public String getAssessmentDate() {
        return assessmentDate;
    }

    public void setAssessmentDate(String assessmentDate) {
        this.assessmentDate = assessmentDate;
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
                ", assessmentDate='" + assessmentDate + '\'' +
                ", assessmentType='" + assessmentType + '\'' +
                '}';
    }
}
