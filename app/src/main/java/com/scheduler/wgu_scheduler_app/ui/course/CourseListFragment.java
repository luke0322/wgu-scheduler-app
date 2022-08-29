package com.scheduler.wgu_scheduler_app.ui.course;

import android.content.Intent;
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
import com.scheduler.wgu_scheduler_app.db.entity.CourseEntity;
import com.scheduler.wgu_scheduler_app.ui.adapters.CourseAdapter;
import com.scheduler.wgu_scheduler_app.ui.utils.Utils;

import java.util.List;

public class CourseListFragment extends Fragment {
    private Handler handler = new Handler();
    private CourseViewModel mViewModel;

    public static com.scheduler.wgu_scheduler_app.ui.course.CourseListFragment newInstance() {
        return new com.scheduler.wgu_scheduler_app.ui.course.CourseListFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.course_list_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        mViewModel.getAllByTermId(Utils.CurrentTermId, getActivity().getApplication(), result -> {
            if (result instanceof Result.Success){
                RecyclerView recyclerView = getActivity().findViewById(R.id.recyclerview_course);
                recyclerView.setNestedScrollingEnabled(false);
                final CourseAdapter adapter = new CourseAdapter(getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                List<CourseEntity> courses = (List<CourseEntity>) ((Result.Success) result).data;
                adapter.setCourses(courses, getActivity());
            }
        }, handler);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            setHasOptionsMenu(true);
            Intent i = getActivity().getIntent();
            Utils.switchFragment(getActivity(), R.id.container_course, CourseFragment.newInstance());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
