package com.scheduler.wgu_scheduler_app.ui.assessment;

import android.app.Application;
import android.os.Handler;
import androidx.lifecycle.ViewModel;
import com.scheduler.wgu_scheduler_app.db.entity.AssessmentEntity;
import com.scheduler.wgu_scheduler_app.db.repository.AssessmentRepository;
import com.scheduler.wgu_scheduler_app.db.repository.RepositoryCallback;

public class AssessmentViewModel extends ViewModel {
    private AssessmentRepository ar;

    public void insert(AssessmentEntity ae, Application application, RepositoryCallback cb, Handler handler) {
        checkRepo(application);
        ar.insert(ae, cb, handler);
    }

    public void update(AssessmentEntity ae, Application app, RepositoryCallback cb, Handler handler) {
        checkRepo(app);
        ar.update(ae, cb, handler);
    }

    public void delete(AssessmentEntity ae, Application app, RepositoryCallback cb, Handler handler) {
        checkRepo(app);
        ar.delete(ae, cb, handler);
    }

    public void getAllByCourseId(int courseId, Application application, RepositoryCallback cb, Handler handler){
        checkRepo(application);
        ar.getAllByCourseId(courseId, cb, handler);
    }

    public void getAssessmentById(int assessmentId, Application application, RepositoryCallback cb, Handler handler){
        checkRepo(application);
        ar.getAssessmentById(assessmentId, cb, handler);
    }

    private void checkRepo(Application app){
        if (ar == null) {
            ar = new AssessmentRepository(app);
        }
    }
}