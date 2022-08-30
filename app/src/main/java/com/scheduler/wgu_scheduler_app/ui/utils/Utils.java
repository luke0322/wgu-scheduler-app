package com.scheduler.wgu_scheduler_app.ui.utils;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.util.Calendar;

public class Utils {

    public static int CurrentTermId = 0;
    public static int CurrentCourseId = 0;

    public static String notificationTitle = "";
    public static String notificationBody = "";

    public static String CHANNEL_ID = "22";
    public static String CHANNEL_NAME = "Date Notification";
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
                (view, yearL, monthOfYear, dayOfMonth) ->
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

    public static boolean isNotBlank(String string){
        return string != null && !string.trim().isEmpty();
    }

    public static boolean isNotBlank(Editable editable){
        if (editable != null) {
            return editable.toString() != null && !editable.toString().trim().isEmpty();
        }
        return false;
    }

    public static String getEditTextToString(EditText editText){
        if (null != editText && isNotBlank(editText.getText())){
            return editText.getText().toString();
        }
        return "";
    }

    public static void sendReminder(long timeInSeconds, Context context, String title, String body){

        notificationTitle = title;
        notificationBody = body;

        NotificationUtils _notificationUtils = new NotificationUtils(context, Utils.CHANNEL_ID, Utils.CHANNEL_NAME);
        long _currentTime = System.currentTimeMillis();
        long _triggerReminder = _currentTime + timeInSeconds;
        _notificationUtils.setReminder(_triggerReminder);
    }
}
