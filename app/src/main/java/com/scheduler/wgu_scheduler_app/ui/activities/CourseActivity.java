package com.scheduler.wgu_scheduler_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.scheduler.wgu_scheduler_app.R;
import com.scheduler.wgu_scheduler_app.ui.course.CourseFragment;
import com.scheduler.wgu_scheduler_app.ui.utils.Utils;

public class CourseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.course_activity);
        if (savedInstanceState == null) {
            Utils.switchFragment(this, R.id.container_course, CourseFragment.newInstance());
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}