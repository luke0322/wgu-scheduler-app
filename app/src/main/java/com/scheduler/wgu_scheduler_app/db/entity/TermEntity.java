package com.scheduler.wgu_scheduler_app.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 *
 */
@Entity(tableName = "term_tbl")
public class TermEntity {
    @PrimaryKey(autoGenerate = true)
    private int termId;
    private String termTitle;
    private String termStartDate;
    private String termEndDate;

    public TermEntity(String termTitle, String termStartDate, String termEndDate) {
        this.termTitle = termTitle;
        this.termStartDate = termStartDate;
        this.termEndDate = termEndDate;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getTermTitle() {
        return termTitle;
    }

    public void setTermTitle(String termTitle) {
        this.termTitle = termTitle;
    }

    public String getTermStartDate() {
        return termStartDate;
    }

    public void setTermStartDate(String termStartDate) {
        this.termStartDate = termStartDate;
    }

    public String getTermEndDate() {
        return termEndDate;
    }

    public void setTermEndDate(String termEndDate) {
        this.termEndDate = termEndDate;
    }

    @Override
    public String toString() {
        return "TermEntity{" +
                "termId=" + termId +
                ", termTitle='" + termTitle + '\'' +
                ", termStartDate='" + termStartDate + '\'' +
                ", termEndDate='" + termEndDate + '\'' +
                '}';
    }
}
