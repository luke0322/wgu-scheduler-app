package com.scheduler.wgu_scheduler_app.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.scheduler.wgu_scheduler_app.db.entity.TermEntity;

import java.util.List;

@Dao
public interface TermDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(TermEntity te);

    @Update
    void update(TermEntity te);

    @Delete
    void delete(TermEntity te);

    @Query("SELECT * FROM term_tbl ORDER BY termId ASC")
    List<TermEntity> getAll();
}
