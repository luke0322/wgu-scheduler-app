package com.scheduler.wgu_scheduler_app.db.repository;

import android.app.Application;
import android.os.Handler;

import com.scheduler.wgu_scheduler_app.db.DbBuilder;
import com.scheduler.wgu_scheduler_app.db.Result;
import com.scheduler.wgu_scheduler_app.db.dao.TermDao;
import com.scheduler.wgu_scheduler_app.db.entity.TermEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TermRepository {

    private TermDao termDao;
    private static int NUMBER_OF_THREADS = 2;
    static final ExecutorService repoExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public TermRepository(Application application){
        DbBuilder dbInstance = DbBuilder.getDbInstance(application);
        termDao = dbInstance.termDao();
    }

    public void insert(TermEntity te, final RepositoryCallback<Boolean> callback, final Handler resultHandler){
        repoExecutor.execute(() -> {
            try {
                termDao.insert(te);
                RepositoryShared.notifyResult(new Result.Success<>(true), callback, resultHandler);
            }
            catch (Exception ex){
                RepositoryShared.notifyResult(new Result.Error<>(ex), callback, resultHandler);
            }
        });
    }

    public void update(TermEntity te, final RepositoryCallback<Boolean> callback, final Handler resultHandler){
        repoExecutor.execute(() -> {
            try {
                termDao.update(te);
                RepositoryShared.notifyResult(new Result.Success<>(true), callback, resultHandler);
            }
            catch (Exception ex){
                RepositoryShared.notifyResult(new Result.Error<>(ex), callback, resultHandler);
            }
        });
    }

    public void getAll(final RepositoryCallback<List<TermEntity>> callback, final Handler resultHandler){
        repoExecutor.execute(() -> {
            try {
                List<TermEntity> terms = termDao.getAll();
                RepositoryShared.notifyResult(new Result.Success<List<TermEntity>>(terms), callback, resultHandler);
            }
            catch (Exception ex){
                RepositoryShared.notifyResult(new Result.Error<>(ex), callback, resultHandler);
            }
        });
    }
}
