package com.scheduler.wgu_scheduler_app.db.repository;

import com.scheduler.wgu_scheduler_app.db.Result;

public interface RepositoryCallback<T> {
    void onComplete(Result<T> result);
}
