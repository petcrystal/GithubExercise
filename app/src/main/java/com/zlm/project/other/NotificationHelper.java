package com.zlm.project.other;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;

import com.zlm.project.R;
import com.zlm.project.ui.users.UsersActivity;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;

public class NotificationHelper {

    //-----------------------------------------------------
    private Context context;

    //-----------------------------------------------------
    public NotificationHelper(Context context) {
        this.context = context;
    }

    //-----------------------------------------------------

    /**
     * 推送推播畫面
     *
     * @param title   標題
     * @param message 訊息
     */
    public void pushNotification(String title, String message, String id) {

        Intent intent = new Intent();
        intent.setClass(context, UsersActivity.class);
        intent.putExtra("id", id);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        String channelId = context.getString(R.string.notification_channel_id);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context, channelId)
                .setPriority(Notification.PRIORITY_HIGH)
                .setSmallIcon(getNotificationIcon())
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_home_red))
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setColor(ActivityCompat.getColor(context, R.color.colorPrimary))
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // On android 8.0 or later need to add channel type.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(channelId,
                    context.getString(R.string.notification_channel_id),
                    NotificationManager.IMPORTANCE_HIGH);
            notificationManager.createNotificationChannel(channel);
        }

        notificationManager.notify(0, notificationBuilder.build());
    }

    //-----------------------------------------------------
    private int getNotificationIcon() {
        boolean useWhiteIcon = (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.ic_home_red : R.mipmap.ic_launcher;
    }
    //-----------------------------------------------------
}
