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
import android.widget.CheckBox;
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

import java.text.ParseException;

public class AssessmentFragment extends Fragment {

    private AssessmentViewModel mViewModel;
    private Handler handler = new Handler();
    private EditText title;
    private Spinner assessmentType;
    private EditText startDate;
    private EditText endDate;
    private Button saveAssessmentButton;
    private Button viewAllAssessmentsButton;
    private CheckBox checkBoxStart;
    private CheckBox checkBoxEnd;

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
        setHasOptionsMenu(true);

        mViewModel = new ViewModelProvider(this).get(AssessmentViewModel.class);

        title = getActivity().findViewById(R.id.editTextName);
        assessmentType =  getActivity().findViewById(R.id.spinner_assessment);
        String[] items = new String[] {"Objective Assessment", "Performance Assessment"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, items);
        assessmentType.setAdapter(adapter);
        assessmentType.setSelection(0);

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

                AssessmentEntity ae = new AssessmentEntity(Utils.CurrentCourseId,
                                        Utils.getEditTextToString(title),
                                        Utils.getEditTextToString(startDate),
                                        Utils.getEditTextToString(endDate),
                                        assessmentType.getSelectedItem().toString());

                if (checkBoxStart.isChecked()){
                    Utils.scheduleNotification(Utils.getNotification("Assessment:" + title.getText().toString() + " start date is today!", "Assessment Notification", getContext()),
                            Utils.getTimeFromDateString(Utils.getEditTextToString(startDate)), getContext(), 3);
                }

                if (checkBoxEnd.isChecked()){
                    Utils.scheduleNotification(Utils.getNotification("Assessment:" + title.getText().toString() + " end date is today!", "Assessment Notification", getContext()),
                            Utils.getTimeFromDateString(Utils.getEditTextToString(endDate)), getContext(),4);
                }


                mViewModel.insert(ae, getActivity().getApplication(), result -> {
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

        viewAllAssessmentsButton = getActivity().findViewById(R.id.view_all_assessments_button);
        viewAllAssessmentsButton.setOnClickListener(l -> {
            Utils.switchFragment(getActivity(), R.id.container_assessment, AssessmentListFragment.newInstance());
        });

        checkBoxStart = getActivity().findViewById(R.id.checboxStart);
        checkBoxStart.setChecked(false);
        checkBoxEnd = getActivity().findViewById(R.id.checboxEnd);
        checkBoxEnd.setChecked(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Utils.switchActivity(CourseActivity.class, getActivity());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
