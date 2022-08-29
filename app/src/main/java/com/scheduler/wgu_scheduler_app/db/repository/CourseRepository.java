package com.scheduler.wgu_scheduler_app.db.repository;

import android.app.Application;
import android.os.Handler;

import com.scheduler.wgu_scheduler_app.db.DbBuilder;
import com.scheduler.wgu_scheduler_app.db.Result;
import com.scheduler.wgu_scheduler_app.db.dao.CourseDao;
import com.scheduler.wgu_scheduler_app.db.entity.CourseEntity;
import com.scheduler.wgu_scheduler_app.db.entity.TermEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CourseRepository {

    private CourseDao courseDao;
    private static int NUMBER_OF_THREADS = 2;
    static final ExecutorService repoExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public CourseRepository(Application application){
        DbBuilder dbInstance = DbBuilder.getDbInstance(application);
        courseDao = dbInstance.courseDao();
    }

    public void insert(CourseEntity ce, final RepositoryCallback<Boolean> callback, final Handler resultHandler){
        repoExecutor.execute(() -> {
            try {
                courseDao.insert(ce);
                RepositoryShared.notifyResult(new Result.Success<>(true), callback, resultHandler);
            }
            catch (Exception ex){
                RepositoryShared.notifyResult(new Result.Error<>(ex), callback, resultHandler);
            }
        });
    }

    public void update(CourseEntity ce, final RepositoryCallback<Boolean> callback, final Handler resultHandler){
        repoExecutor.execute(() -> {
            try {
                courseDao.update(ce);
                RepositoryShared.notifyResult(new Result.Success<>(true), callback, resultHandler);
            }
            catch (Exception ex){
                RepositoryShared.notifyResult(new Result.Error<>(ex), callback, resultHandler);
            }
        });
    }

    public void delete(CourseEntity ce, final RepositoryCallback<Boolean> callback, final Handler resultHandler){
        repoExecutor.execute(() -> {
            try {
                courseDao.delete(ce);
                RepositoryShared.notifyResult(new Result.Success<>(true), callback, resultHandler);
            }
            catch (Exception ex){
                RepositoryShared.notifyResult(new Result.Error<>(ex), callback, resultHandler);
            }
        });
    }


    public void getAll(final RepositoryCallback<List<CourseEntity>> callback, final Handler resultHandler){
        repoExecutor.execute(() -> {
            try {
                List<CourseEntity> courses = courseDao.getAll();
                RepositoryShared.notifyResult(new Result.Success<>(courses), callback, resultHandler);
            }
            catch (Exception ex){
                RepositoryShared.notifyResult(new Result.Error<>(ex), callback, resultHandler);
            }
        });
    }

    public void getAllByTermId(int termId, final RepositoryCallback<List<CourseEntity>> callback, final Handler resultHandler){
        repoExecutor.execute(() -> {
            try {
                List<CourseEntity> courses = courseDao.getAllByTermId(termId);
                RepositoryShared.notifyResult(new Result.Success<>(courses), callback, resultHandler);
            }
            catch (Exception ex){
                RepositoryShared.notifyResult(new Result.Error<>(ex), callback, resultHandler);
            }
        });
    }

    public void getCourseById(int courseId, final RepositoryCallback<CourseEntity> callback, final Handler resultHandler){
        repoExecutor.execute(() -> {
            try {
                CourseEntity course = courseDao.getCourseById(courseId);
                RepositoryShared.notifyResult(new Result.Success<>(course), callback, resultHandler);
            }
            catch (Exception ex){
                RepositoryShared.notifyResult(new Result.Error<>(ex), callback, resultHandler);
            }
        });
    }

}
