package com.scheduler.wgu_scheduler_app.ui.main;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
import com.scheduler.wgu_scheduler_app.ui.activities.MainActivity;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;
    private Button courseListButton;
    private Button assessmentListButton;
    private Button termListButton;

    @Override
    public void onStart() {
        super.onStart();
        courseListButton = getView().findViewById(R.id.button);
        courseListButton.setOnClickListener(v -> {
        });

        assessmentListButton = getView().findViewById(R.id.button2);
        assessmentListButton.setOnClickListener(v -> {
            switchActivity(AssessmentActivity.class);
        });

        termListButton = getView().findViewById(R.id.button3);
        termListButton.setOnClickListener(v -> {
        });

    }

    private void switchActivity(Class cls){
        Intent intent = new Intent(getActivity(), cls);
        startActivity(intent);
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
        mViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

}