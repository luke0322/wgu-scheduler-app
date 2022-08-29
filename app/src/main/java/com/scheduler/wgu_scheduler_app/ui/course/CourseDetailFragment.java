package com.scheduler.wgu_scheduler_app.ui.course;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.InputType;
import android.util.Log;
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
import com.scheduler.wgu_scheduler_app.db.entity.CourseEntity;
import com.scheduler.wgu_scheduler_app.ui.activities.AssessmentActivity;
import com.scheduler.wgu_scheduler_app.ui.activities.TermActivity;
import com.scheduler.wgu_scheduler_app.ui.term.TermDetailFragment;
import com.scheduler.wgu_scheduler_app.ui.term.TermFragment;
import com.scheduler.wgu_scheduler_app.ui.term.TermListFragment;
import com.scheduler.wgu_scheduler_app.ui.utils.Utils;

import java.util.List;

public class CourseDetailFragment extends Fragment {

    private Handler handler = new Handler();
    private CourseViewModel mViewModel;
    private EditText courseName;
    private EditText instructorNames;
    private EditText emailAddresses;
    private EditText phoneNumbers;
    private EditText startDate;
    private EditText endDate;
    private Spinner courseStatus;
    private Button deleteCourseButton;
    private Button saveCourseButton;
    private Button addAssessmentButton;

    private int courseId;
    private int termId;


    public static com.scheduler.wgu_scheduler_app.ui.course.CourseDetailFragment newInstance() {
        return new com.scheduler.wgu_scheduler_app.ui.course.CourseDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        courseId = getArguments().getInt("courseId");
        termId = getArguments().getInt("termId");
        return inflater.inflate(R.layout.course_detail_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        courseName = getActivity().findViewById(R.id.editTextCourseName);
        instructorNames = getActivity().findViewById(R.id.editTextInstructorNames);
        emailAddresses = getActivity().findViewById(R.id.editTextEmailAddresses);
        phoneNumbers = getActivity().findViewById(R.id.editTextPhoneNumbers);

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

        courseStatus = getActivity().findViewById(R.id.spinner);
        String[] items = new String[] {"in progress", "completed", "dropped", "plan to take"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.support_simple_spinner_dropdown_item, items);
        courseStatus.setAdapter(adapter);

        mViewModel.getCourseById(courseId, getActivity().getApplication(), result -> {
            if (result instanceof Result.Success) {
                CourseEntity course = (CourseEntity) ((Result.Success) result).data;

                if (course != null){
                    courseName.setText(course.getCourseTitle());
                    instructorNames.setText(course.getCourseInstructorNames());
                    emailAddresses.setText(course.getCourseInstructorEmailAddresses());
                    phoneNumbers.setText(course.getCourseInstructorPhoneNumbers());
                    startDate.setText(course.getCourseStartDate());
                    endDate.setText(course.getCourseEndDate());
                    switch(course.getCourseStatus()){
                        case "in progress":
                        default:
                            courseStatus.setSelection(0, false);
                            break;
                        case "completed":
                            courseStatus.setSelection(1, false);
                            break;
                        case "dropped":
                            courseStatus.setSelection(2, false);
                            break;
                        case "plan to take":
                            courseStatus.setSelection(3, false);
                            break;
                    }

                }
            }
            else {
                Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
            }
        }, handler);

        deleteCourseButton = getActivity().findViewById(R.id.delete_course_button);
        deleteCourseButton.setOnClickListener(l -> {
            if (Utils.isNotBlank(courseName.getText()) &&
                    Utils.isNotBlank(instructorNames.getText()) &&
                    Utils.isNotBlank(emailAddresses.getText()) &&
                    Utils.isNotBlank(phoneNumbers.getText()) &&
                    Utils.isNotBlank(startDate.getText()) &&
                    Utils.isNotBlank(endDate.getText())) {

                CourseEntity ce = new CourseEntity(termId,
                        Utils.getEditTextToString(courseName),
                        Utils.getEditTextToString(startDate),
                        Utils.getEditTextToString(endDate),
                        courseStatus.getSelectedItem().toString(),
                        Utils.getEditTextToString(instructorNames),
                        Utils.getEditTextToString(emailAddresses),
                        Utils.getEditTextToString(phoneNumbers));

                ce.setCourseId(courseId);

                mViewModel.delete(ce, getActivity().getApplication(), result -> {
                    if (result instanceof Result.Success){
                        Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                        Utils.switchFragment(getActivity(), R.id.container_course, CourseListFragment.newInstance());
                    }
                    else {
                        Toast.makeText(getContext(), "FAIL", Toast.LENGTH_SHORT).show();
                    }
                }, handler);
            }
            else {
                Toast.makeText(getContext(), "One of the fields was empty to delete!", Toast.LENGTH_SHORT).show();
            }
        });

        saveCourseButton = getActivity().findViewById(R.id.save_course_button);
        saveCourseButton.setOnClickListener(l -> {
            if (Utils.isNotBlank(courseName.getText()) &&
                    Utils.isNotBlank(instructorNames.getText()) &&
                    Utils.isNotBlank(emailAddresses.getText()) &&
                    Utils.isNotBlank(phoneNumbers.getText()) &&
                    Utils.isNotBlank(startDate.getText()) &&
                    Utils.isNotBlank(endDate.getText())) {

                CourseEntity ce = new CourseEntity(termId,
                        Utils.getEditTextToString(courseName),
                        Utils.getEditTextToString(startDate),
                        Utils.getEditTextToString(endDate),
                        courseStatus.getSelectedItem().toString(),
                        Utils.getEditTextToString(instructorNames),
                        Utils.getEditTextToString(emailAddresses),
                        Utils.getEditTextToString(phoneNumbers));

                ce.setCourseId(courseId);

                mViewModel.update(ce, getActivity().getApplication(), result -> {
                    if (result instanceof Result.Success){
                        Toast.makeText(getContext(), "SUCCESS", Toast.LENGTH_SHORT).show();
                        Utils.switchFragment(getActivity(), R.id.container_course, CourseListFragment.newInstance());
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

        addAssessmentButton = getActivity().findViewById(R.id.add_assessment_button);
        addAssessmentButton.setOnClickListener(l -> {
            Utils.CurrentCourseId = courseId;
            Utils.switchActivity(AssessmentActivity.class, getActivity());
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            Utils.switchFragment(getActivity(), R.id.container_course, CourseListFragment.newInstance());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}

