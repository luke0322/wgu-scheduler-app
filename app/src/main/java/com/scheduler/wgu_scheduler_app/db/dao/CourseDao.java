package com.scheduler.wgu_scheduler_app.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.scheduler.wgu_scheduler_app.db.entity.CourseEntity;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CourseEntity ce);

    @Update
    void update(CourseEntity ce);

    @Delete
    void delete(CourseEntity ce);

    @Query("SELECT * FROM course_tbl ORDER BY courseId ASC")
    List<CourseEntity> getAll();

    @Query("SELECT * FROM course_tbl WHERE termId = :termId ORDER BY courseId ASC")
    List<CourseEntity> getAllByTermId(int termId);

    @Query("SELECT * FROM course_tbl WHERE courseId = :courseId")
    CourseEntity getCourseById(int courseId);
}
