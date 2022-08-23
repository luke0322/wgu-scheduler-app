package com.scheduler.wgu_scheduler_app.db.repository;

import android.app.Application;
import android.os.Handler;

import com.scheduler.wgu_scheduler_app.db.DbBuilder;
import com.scheduler.wgu_scheduler_app.db.Result;
import com.scheduler.wgu_scheduler_app.db.dao.AssessmentDao;
import com.scheduler.wgu_scheduler_app.db.entity.AssessmentEntity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AssessmentRepository {

    private AssessmentDao assessmentDao;
    private static int NUMBER_OF_THREADS = 2;
    static final ExecutorService repoExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public AssessmentRepository(Application application){
        DbBuilder dbInstance = DbBuilder.getDbInstance(application);
        assessmentDao = dbInstance.assessmentDao();
    }

    public void insert(AssessmentEntity ae, final RepositoryCallback<Boolean> callback, final Handler resultHandler){
        repoExecutor.execute(() -> {
            try {
                assessmentDao.insert(ae);
                RepositoryShared.notifyResult(new Result.Success<>(true), callback, resultHandler);
            }
            catch (Exception ex){
                RepositoryShared.notifyResult(new Result.Error<>(ex), callback, resultHandler);
            }
        });
    }
}
