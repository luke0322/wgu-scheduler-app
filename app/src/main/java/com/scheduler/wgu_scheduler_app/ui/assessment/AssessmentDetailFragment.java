package com.scheduler.wgu_scheduler_app.ui.assessment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.scheduler.wgu_scheduler_app.R;
import com.scheduler.wgu_scheduler_app.db.Result;
import com.scheduler.wgu_scheduler_app.db.entity.AssessmentEntity;
import com.scheduler.wgu_scheduler_app.db.entity.CourseEntity;
import com.scheduler.wgu_scheduler_app.ui.activities.AssessmentActivity;
import com.scheduler.wgu_scheduler_app.ui.activities.CourseActivity;
import com.scheduler.wgu_scheduler_app.ui.activities.TermActivity;
import com.scheduler.wgu_scheduler_app.ui.course.CourseListFragment;
import com.scheduler.wgu_scheduler_app.ui.utils.Utils;

public class AssessmentDetailFragment extends Fragment {

    private AssessmentViewModel mViewModel;
    private Handler handler = new Handler();
    private EditText title;
    private Spinner assessmentType;
    private EditText startDate;
    private EditText endDate;
    private Button saveAssessmentButton;
    private Button deleteAssessmentButton;

    private int assessmentId;
    private int courseId;

    public static AssessmentDetailFragment newInstance() {
        return new AssessmentDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        courseId = getArguments().getInt("courseId");
        assessmentId = getArguments().getInt("assessmentId");
        return inflater.inflate(R.layout.assessment_detail_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);

        mViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);

        title = getActivity().findViewById(R.id.editTextName);
        assessmentType =  getActivity().findViewById(R.id.spinner_assessment);
        String[] items = new String[] {"Objective Assessment", "Performance Assessment"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, items);
        assessmentType.setAdapter(adapter);

        startDate = getActivity().findViewById(R.id.editTextStartDate);
        startDate.setInputType(InputType.TYPE_NULL);
        startDate.setOnClickListener(l -> {
            Utils.toggleSoftKeyboard(getActivity(), false);
            Utils.showDatePicker(getContext(), startDate);
        });

        startDate.setOnFocusChangeListener((view, focused) -> {
            if (focused){
                Utils.toggleSoftKeyboard(getActivity(), false);
                Utils.showDatePicker(getContext(), startDate);
            }
        });

        endDate = getActivity().findViewById(R.id.editTextEndDate);
        endDate.setInputType(InputType.TYPE_NULL);
        endDate.setOnClickListener(l -> {
            Utils.toggleSoftKeyboard(getActivity(), false);
            Utils.showDatePicker(getContext(), endDate);
        });

        endDate.setOnFocusChangeListener((view, focused) -> {
            if (focused){
                Utils.toggleSoftKeyboard(getActivity(), false);
                Utils.showDatePicker(getContext(), endDate);
            }
        });

        saveAssessmentButton = getActivity().findViewById(R.id.save_assessment_button);
        saveAssessmentButton.setOnClickListener(l -> {
            if (Utils.isNotBlank(title.getText()) &&
                    Utils.isNotBlank(startDate.getText()) &&
                    Utils.isNotBlank(endDate.getText())) {

                AssessmentEntity ae = new AssessmentEntity(courseId,
                        Utils.getEditTextToString(title),
                        Utils.getEditTextToString(startDate),
                        Utils.getEditTextToString(endDate),
                        assessmentType.getSelectedItem().toString());

                ae.setAssessmentId(assessmentId);

                mViewModel.update(ae, getActivity().getApplication(), result -> {
                    if (result instanceof Result.Success){
                        Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
                    }
                }, handler);
            }
            else {
                Toast.makeText(getContext(), "One of the fields was empty!", Toast.LENGTH_SHORT).show();
            }
        });

        deleteAssessmentButton = getActivity().findViewById(R.id.delete_assessment_button);
        deleteAssessmentButton.setOnClickListener(l -> {

            if (Utils.isNotBlank(title.getText()) &&
                    Utils.isNotBlank(startDate.getText()) &&
                    Utils.isNotBlank(endDate.getText())) {

                AssessmentEntity ae = new AssessmentEntity(courseId,
                        Utils.getEditTextToString(title),
                        Utils.getEditTextToString(startDate),
                        Utils.getEditTextToString(endDate),
                        assessmentType.getSelectedItem().toString());

                ae.setAssessmentId(assessmentId);

                mViewModel.delete(ae, getActivity().getApplication(), result -> {
                    if (result instanceof Result.Success){
                        Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                        Utils.switchFragment(getActivity(), R.id.container_assessment, AssessmentListFragment.newInstance());
                    }
                    else {
                        Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
                    }
                }, handler);
            }
            else {
                Toast.makeText(getContext(), "One of the fields was empty!", Toast.LENGTH_SHORT).show();
            }

        });

        mViewModel.getAssessmentById(assessmentId, getActivity().getApplication(), result -> {
            if (result instanceof Result.Success) {
                AssessmentEntity assessment = (AssessmentEntity) ((Result.Success) result).data;
                if (assessment != null){
                    title.setText(assessment.getAssessmentName());
                    startDate.setText(assessment.getAssessmentStartDate());
                    endDate.setText(assessment.getAssessmentEndDate());

                    switch(assessment.getAssessmentType()){
                        case "Objective Assessment":
                        default:
                            assessmentType.setSelection(0, false);
                            break;
                        case "Performance Assessment":
                            assessmentType.setSelection(1, false);
                            break;
                    }
                }

            }
            else {
                Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
            }
        }, handler);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Utils.switchFragment(getActivity(), R.id.container_assessment, AssessmentListFragment.newInstance());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
