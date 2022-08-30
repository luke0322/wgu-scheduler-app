package com.scheduler.wgu_scheduler_app.ui.utils;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;

import com.scheduler.wgu_scheduler_app.R;
import com.scheduler.wgu_scheduler_app.ui.receiver.ReminderReceiver;

public class NotificationUtils extends ContextWrapper
{

    private NotificationManager _notificationManager;
    private Context _context;
    private String _channelId;
    private String _channelName;

    public NotificationUtils(Context base, String channelId, String channelName)
    {
        super(base);
        _context = base;
        this._channelId = channelId;
        this._channelName = channelName;
        createChannel();
    }

    public NotificationCompat.Builder setNotification(String title, String message)
    {
        return new NotificationCompat.Builder(this, this._channelId)
                .setSmallIcon(R.drawable.ic_action_profile)
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
    }

    private void createChannel()
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel(this._channelId, this._channelName, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            getManager().createNotificationChannel(channel);
        }
    }

    public NotificationManager getManager()
    {
        if(_notificationManager == null)
        {
            _notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return _notificationManager;
    }

    public void setReminder(long timeInMillis)
    {
        Intent _intent = new Intent(_context, ReminderReceiver.class);
        PendingIntent _pendingIntent = PendingIntent.getBroadcast(_context, 0, _intent, 0);

        AlarmManager _alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        _alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMillis, _pendingIntent);
    }

}
