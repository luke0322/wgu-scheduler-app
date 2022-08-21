package com.scheduler.wgu_scheduler_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.scheduler.wgu_scheduler_app.R;
import com.scheduler.wgu_scheduler_app.ui.assessment.AssessmentFragment;

public class AssessmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.assessment_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_assessment, AssessmentFragment.newInstance())
                    .commitNow();
        }
    }
}