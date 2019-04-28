package com.example.guest.lkce;

import android.app.NotificationManager;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.media.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyMessagingService extends FirebaseMessagingService {


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        showNotification(remoteMessage.getNotification().getTitle(), remoteMessage.getNotification().getBody());

    }

    public void showNotification(String title, String message) {
        android.support.v4.app.NotificationCompat.Builder builder = new android.support.v4.app.NotificationCompat.Builder(this, "mynotification").setContentTitle(title).setSmallIcon(R.drawable.ic_launcher_background).setAutoCancel(true).setContentText(message);
        NotificationManagerCompat manger = NotificationManagerCompat.from(this);
        manger.notify(999, builder.build());
        System.out.println("*********************************************************************************************" + title);
    }
}
