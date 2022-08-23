package com.scheduler.wgu_scheduler_app.ui.term;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.scheduler.wgu_scheduler_app.R;
import com.scheduler.wgu_scheduler_app.db.Result;
import com.scheduler.wgu_scheduler_app.db.entity.TermEntity;
import com.scheduler.wgu_scheduler_app.ui.activities.MainActivity;
import com.scheduler.wgu_scheduler_app.ui.activities.TermActivity;
import com.scheduler.wgu_scheduler_app.ui.main.MainFragment;
import com.scheduler.wgu_scheduler_app.ui.utils.Utils;

public class TermFragment extends Fragment {

    private Handler handler = new Handler();
    private TermViewModel mViewModel;
    private EditText termName;
    private EditText startDate;
    private EditText endDate;
    private Button saveTermButton;
    private Button allTermButton;

    public static com.scheduler.wgu_scheduler_app.ui.term.TermFragment newInstance() {
        return new com.scheduler.wgu_scheduler_app.ui.term.TermFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.term_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        mViewModel = new ViewModelProvider(this).get(TermViewModel.class);
        termName = getActivity().findViewById(R.id.editTextTermName);
        startDate = getActivity().findViewById(R.id.editTextStartDate);
        endDate = getActivity().findViewById(R.id.editTextEndDate);

        allTermButton = getActivity().findViewById(R.id.view_all_terms_button);
        allTermButton.setOnClickListener(v -> {
            Utils.switchFragment(getActivity(), R.id.container_term, TermListFragment.newInstance());
        });

        saveTermButton = getActivity().findViewById(R.id.save_term_button);
        saveTermButton.setOnClickListener(v -> {
            if (termName.getText() != null &&
                    startDate.getText() != null &&
                    endDate.getText() != null) {
                mViewModel.insert(new TermEntity(
                        termName.getText().toString(),
                        startDate.getText().toString(),
                        endDate.getText().toString()), getActivity().getApplication(), result -> {
                    if (result instanceof Result.Success){
                        Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
                    }
                }, handler);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Utils.switchActivity(MainActivity.class, getActivity());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}