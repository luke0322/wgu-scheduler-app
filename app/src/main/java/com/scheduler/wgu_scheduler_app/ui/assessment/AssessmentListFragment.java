package com.scheduler.wgu_scheduler_app.ui.assessment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scheduler.wgu_scheduler_app.R;
import com.scheduler.wgu_scheduler_app.db.Result;
import com.scheduler.wgu_scheduler_app.db.entity.AssessmentEntity;
import com.scheduler.wgu_scheduler_app.ui.adapters.AssessmentAdapter;
import com.scheduler.wgu_scheduler_app.ui.utils.Utils;

import java.util.List;

public class AssessmentListFragment extends Fragment {
    private Handler handler = new Handler();
    private AssessmentViewModel mViewModel;

    public static com.scheduler.wgu_scheduler_app.ui.assessment.AssessmentListFragment newInstance() {
        return new com.scheduler.wgu_scheduler_app.ui.assessment.AssessmentListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.assessment_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);
        mViewModel.getAllByCourseId(Utils.CurrentCourseId, getActivity().getApplication(), result -> {
            if (result instanceof Result.Success){
                RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerview_assessment);
                recyclerView.setNestedScrollingEnabled(false);
                final AssessmentAdapter adapter = new AssessmentAdapter(getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                List<AssessmentEntity> assessments = (List<AssessmentEntity>) ((Result.Success) result).data;
                adapter.setAssessments(assessments, getActivity());
            }
        }, handler);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Utils.switchFragment(getActivity(), R.id.container_assessment, AssessmentFragment.newInstance());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
