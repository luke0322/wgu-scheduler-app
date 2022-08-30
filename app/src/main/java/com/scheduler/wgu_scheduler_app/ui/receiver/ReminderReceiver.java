package com.scheduler.wgu_scheduler_app.ui.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;

import com.scheduler.wgu_scheduler_app.ui.utils.NotificationUtils;
import com.scheduler.wgu_scheduler_app.ui.utils.Utils;

public class ReminderReceiver extends BroadcastReceiver
{
    private int reminderId = 250;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        ++reminderId;
        String title = Utils.getPreference(context, "title");
        String body = Utils.getPreference(context, "body");

        NotificationUtils _notificationUtils = new NotificationUtils(context, Utils.CHANNEL_ID, Utils.CHANNEL_NAME);
        NotificationCompat.Builder _builder = _notificationUtils.setNotification(title, body);
        _notificationUtils.getManager().notify(reminderId, _builder.build());
    }
}
