package com.scheduler.wgu_scheduler_app.db.repository;

import android.os.Handler;

import com.scheduler.wgu_scheduler_app.db.Result;

public class RepositoryShared {

    public static <T> void notifyResult(final Result<T> result, final RepositoryCallback<T> callback, final Handler resultHandler){
        resultHandler.post(() -> callback.onComplete(result));
    }
}
