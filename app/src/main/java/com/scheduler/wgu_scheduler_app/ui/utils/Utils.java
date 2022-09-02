package com.scheduler.wgu_scheduler_app.ui.utils;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.scheduler.wgu_scheduler_app.R;
import com.scheduler.wgu_scheduler_app.ui.receiver.ReminderReceiver;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {

    public static int CurrentTermId = 0;
    public static int CurrentCourseId = 0;

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

    private static boolean isTodaysDate(String date){
        if (isNotBlank(date)){
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
            try {
                Date date1 = sdf.parse(date);
                return DateUtils.isToday(date1.getTime());
            }
            catch (ParseException pe){
                pe.printStackTrace();
            }
        }
        return false;
    }


    public static long getTimeFromDateString(String date){
        if (isTodaysDate(date)) return 0;

        if (isNotBlank(date)){
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
            try {
                Date date1 = sdf.parse(date);
                Log.d("UTILSSS", "Date is: " + date1);
                return date1.getTime();
            }
            catch (ParseException pe){
                pe.printStackTrace();
            }
        }

        return 0;
    }

    public static void scheduleNotification (Notification notification , long delay, Context context, int id) {
        Intent notificationIntent = new Intent(context, ReminderReceiver.class ) ;
        notificationIntent.putExtra(ReminderReceiver.NOTIFICATION_ID , id) ;
        notificationIntent.putExtra(ReminderReceiver.NOTIFICATION , notification) ;
        PendingIntent pendingIntent = PendingIntent.getBroadcast( context, 0 , notificationIntent , PendingIntent.FLAG_UPDATE_CURRENT ) ;
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE ) ;
        assert alarmManager != null;
        alarmManager.set(AlarmManager.RTC_WAKEUP , delay , pendingIntent) ;
    }

    public static Notification getNotification (String content, String title, Context context) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, ReminderReceiver.CHANNEL_ID);
        builder.setContentTitle(title) ;
        builder.setContentText(content) ;
        builder.setSmallIcon(R.drawable.ic_action_profile);
        builder.setAutoCancel( true ) ;
        builder.setChannelId(ReminderReceiver.CHANNEL_ID) ;
        return builder.build() ;
    }
}
