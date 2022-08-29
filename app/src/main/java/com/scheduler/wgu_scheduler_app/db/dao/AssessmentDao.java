package com.scheduler.wgu_scheduler_app.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.scheduler.wgu_scheduler_app.db.entity.AssessmentEntity;
import com.scheduler.wgu_scheduler_app.db.entity.CourseEntity;

import java.util.List;

@Dao
public interface AssessmentDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(AssessmentEntity ae);

    @Update
    void update(AssessmentEntity ae);

    @Delete
    void delete(AssessmentEntity ae);

    @Query("SELECT * FROM assessment_tbl ORDER BY assessmentId ASC")
    List<AssessmentEntity> getAll();

    @Query("SELECT * FROM assessment_tbl WHERE courseId = :courseId ORDER BY courseId ASC")
    List<AssessmentEntity> getAllByCourseId(int courseId);

    @Query("SELECT * FROM assessment_tbl WHERE assessmentId = :assessmentId")
    AssessmentEntity getAssessmentById(int assessmentId);
}
