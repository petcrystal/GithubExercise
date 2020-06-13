package com.zlm.project.other.fcm;

import android.app.ActivityManager;
import android.content.Context;

import com.zlm.project.R;
import com.zlm.project.other.NotificationHelper;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.orhanobut.logger.Logger;

import java.util.List;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    // -------------------------------------------
    @Override
    public void onNewToken(String token) {
        Logger.e("Refreshed token: " + token);
    }

    // -------------------------------------------
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if (remoteMessage != null) {
            if (isAppOnForeground()) {
                sendNotification(remoteMessage.getNotification());
            } else {
                sendNotification(remoteMessage.getNotification());
            }
        }
    }

    // -------------------------------------------
    private void sendNotification(RemoteMessage.Notification msg) {

        NotificationHelper notification = new NotificationHelper(this);
        notification.pushNotification((msg.getTitle() != null && msg.getTitle().length() > 0) ? msg.getTitle() : getString(R.string.app_name), msg.getBody(), "0");
    }

    // -------------------------------------------
    private boolean isAppOnForeground() {

        List<ActivityManager.RunningTaskInfo> tasksInfo = ((ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1);
        if (tasksInfo.size() > 0) {
            if (getPackageName().equals(tasksInfo.get(0).topActivity.getPackageName())) {
                return true;
            }
        }
        return false;
    }

    // -------------------------------------------
}
