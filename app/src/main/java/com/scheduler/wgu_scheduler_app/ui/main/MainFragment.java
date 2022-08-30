package com.scheduler.wgu_scheduler_app.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.scheduler.wgu_scheduler_app.R;
import com.scheduler.wgu_scheduler_app.ui.activities.AssessmentActivity;
import com.scheduler.wgu_scheduler_app.ui.activities.CourseActivity;
import com.scheduler.wgu_scheduler_app.ui.activities.TermActivity;
import com.scheduler.wgu_scheduler_app.ui.utils.Utils;

public class MainFragment extends Fragment {

    private Button termButton;

    @Override
    public void onStart() {
        super.onStart();
        termButton = getView().findViewById(R.id.button3);
        termButton.setOnClickListener(v -> {
            Utils.switchActivity(TermActivity.class, getActivity());
        });
    }

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}