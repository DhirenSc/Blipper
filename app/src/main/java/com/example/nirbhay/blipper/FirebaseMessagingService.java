package com.example.nirbhay.blipper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.RemoteMessage;

/**
* Created by Nirbhay Pherwani on 16/11/2016
        */

public class FirebaseMessagingService extends com.google.firebase.messaging.FirebaseMessagingService {

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        showNotification(remoteMessage.getData().get("message"));
    }

    private void showNotification(String message) {

        Intent i = new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,i,PendingIntent.FLAG_UPDATE_CURRENT);

        String em[];
        em = message.split("@@@@");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setAutoCancel(true)
                .setContentTitle("EMERGENCY")
                .setContentText(em[0])
                .setSmallIcon(R.drawable.ic_add_alert_black_24dp)
                .setContentIntent(pendingIntent);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        manager.notify(0,builder.build());


        Intent x = new Intent();
        x.setClass(this, EmergencyActivity.class);
        x.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        x.putExtra("emergency_id",em[1]);
        startActivity(x);


    }
}
