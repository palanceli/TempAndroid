package com.bnrg.photogallery;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

/**
 * Created by palance on 2017/11/20.
 */

public class NotificationReceiver extends BroadcastReceiver {
    private static final String TAG = "NotificationReceiver";

    @Override
    public void onReceive(Context c,Intent i){
        Log.i(TAG, "received result: " + getResultCode());
        if(getResultCode() != Activity.RESULT_OK){
            return;
        }
        int requestCode = i.getIntExtra(PollService.REQUEST_CODE, 0);
        NotificationChannel notificationChannel =
                (NotificationChannel)i.getParcelableExtra(PollService.NOTIFICATION_CHANNEL);
        Notification notification =
                (Notification)i.getParcelableExtra(PollService.NOTIFICATION);

        NotificationManager notificationManager =
                (NotificationManager)c.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);
        notificationManager.notify(0, notification);
    }
}
