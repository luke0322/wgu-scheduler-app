package com.scheduler.wgu_scheduler_app.ui.assessment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.scheduler.wgu_scheduler_app.R;
import com.scheduler.wgu_scheduler_app.db.Result;
import com.scheduler.wgu_scheduler_app.db.entity.AssessmentEntity;

public class AssessmentFragment extends Fragment {

    private AssessmentViewModel mViewModel;
    private Handler handler = new Handler();

    public static AssessmentFragment newInstance() {
        return new AssessmentFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.assessment_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
//        mViewModel.insert(new AssessmentEntity(1, 1, "Name", "Fist date", "Type"),
//                          getActivity().getApplication(), result -> {
//                    if (result instanceof Result.Success){
//                        Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
//                    }
//                }, handler);
    }

}
