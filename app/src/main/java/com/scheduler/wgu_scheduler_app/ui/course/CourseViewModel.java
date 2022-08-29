package com.scheduler.wgu_scheduler_app.ui.course;

import android.app.Application;
import android.os.Handler;

import androidx.lifecycle.ViewModel;

import com.scheduler.wgu_scheduler_app.db.entity.CourseEntity;
import com.scheduler.wgu_scheduler_app.db.repository.CourseRepository;
import com.scheduler.wgu_scheduler_app.db.repository.RepositoryCallback;

public class CourseViewModel extends ViewModel {
    private CourseRepository cr;

    public void insert(CourseEntity ce, Application app, RepositoryCallback cb, Handler handler) {
        checkRepo(app);
        cr.insert(ce, cb, handler);
    }

    public void getAll(Application app, RepositoryCallback cb, Handler handler){
        checkRepo(app);
        cr.getAll(cb, handler);
    }
    public void getAllByTermId(int termId, Application app, RepositoryCallback cb, Handler handler){
        checkRepo(app);
        cr.getAllByTermId(termId, cb, handler);
    }


    private void checkRepo(Application app){
        if (cr == null) {
            cr = new CourseRepository(app);
        }
    }
}
