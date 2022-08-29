package com.scheduler.wgu_scheduler_app.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.scheduler.wgu_scheduler_app.db.dao.AssessmentDao;
import com.scheduler.wgu_scheduler_app.db.dao.CourseDao;
import com.scheduler.wgu_scheduler_app.db.dao.TermDao;
import com.scheduler.wgu_scheduler_app.db.entity.AssessmentEntity;
import com.scheduler.wgu_scheduler_app.db.entity.CourseEntity;
import com.scheduler.wgu_scheduler_app.db.entity.TermEntity;

@Database(entities = {AssessmentEntity.class, CourseEntity.class, TermEntity.class},
          version = 9,
          exportSchema = false) // when you change the db, increment the version
public abstract class DbBuilder extends RoomDatabase {
    public abstract AssessmentDao assessmentDao();
    public abstract CourseDao courseDao();
    public abstract TermDao termDao();

    private static volatile DbBuilder INSTANCE;
    public static String DB_NAME = "schedulerApp.db";

    public static DbBuilder getDbInstance(final Context context) {
            synchronized (DbBuilder.class){
                if (INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DbBuilder.class, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .build();
                }
            }
        return INSTANCE;
    }
}
