package com.scheduler.wgu_scheduler_app.ui.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.Calendar;

public class Utils {

    /**
     * Generic activity switcher.
     * @param cls the activity to start
     * @param fa the current active fragment activity
     */
    public static void switchActivity(Class cls, FragmentActivity fa) {
        Intent intent = new Intent(fa, cls);
        fa.startActivity(intent);
    }

    /**
     * Generic fragment switcher.
     * @param fa the fragment activity
     * @param id the new id to replace with
     * @param fragment the new instance of the fragment
     */
    public static void switchFragment(FragmentActivity fa, int id, Fragment fragment) {
        fa.getSupportFragmentManager().beginTransaction()
                .replace(id, fragment)
                .commitNow();
    }

    public static void showDatePicker(Context context, EditText date){
        final Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        DatePickerDialog dpDialog = new DatePickerDialog(context,
                (DatePickerDialog.OnDateSetListener) (view, yearL, monthOfYear, dayOfMonth) ->
                        date.setText((monthOfYear + 1) + "-" + dayOfMonth + "-" + yearL), year, month, day);
        dpDialog.show();
    }

    public static void toggleSoftKeyboard(Activity activity, boolean isShown) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);

        View view = activity.getCurrentFocus();

        if (view == null) {
            view = new View(activity);
        }

        if (isShown){
            imm.toggleSoftInput(0, 1);
        }
        else {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }
}
