package com.scheduler.wgu_scheduler_app.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.scheduler.wgu_scheduler_app.R;
import com.scheduler.wgu_scheduler_app.ui.main.MainFragment;
import com.scheduler.wgu_scheduler_app.ui.utils.Utils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        if (savedInstanceState == null) {
            Utils.switchFragment(this, R.id.container, MainFragment.newInstance());
        }

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_baseline_sticky_note_2_24);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
    }
}