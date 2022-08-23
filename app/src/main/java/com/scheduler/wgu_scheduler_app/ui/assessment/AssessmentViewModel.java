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
        if (ar == null) {
            ar = new AssessmentRepository(application);
        }
        ar.insert(ae, cb, handler);
    }
}