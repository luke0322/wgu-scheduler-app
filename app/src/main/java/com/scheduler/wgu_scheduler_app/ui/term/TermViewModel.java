package com.scheduler.wgu_scheduler_app.ui.term;

import android.app.Application;
import android.os.Handler;

import androidx.lifecycle.ViewModel;

import com.scheduler.wgu_scheduler_app.db.entity.TermEntity;
import com.scheduler.wgu_scheduler_app.db.repository.RepositoryCallback;
import com.scheduler.wgu_scheduler_app.db.repository.TermRepository;

public class TermViewModel extends ViewModel {
    private TermRepository tr;

    public void insert(TermEntity te, Application app, RepositoryCallback cb, Handler handler) {
        checkRepo(app);
        tr.insert(te, cb, handler);
    }

    public void update(TermEntity te, Application app, RepositoryCallback cb, Handler handler) {
        checkRepo(app);
        tr.update(te, cb, handler);
    }

    public void getAll(Application app, RepositoryCallback cb, Handler handler){
        checkRepo(app);
        tr.getAll(cb, handler);
    }

    private void checkRepo(Application app){
        if (tr == null) {
            tr = new TermRepository(app);
        }
    }
}
