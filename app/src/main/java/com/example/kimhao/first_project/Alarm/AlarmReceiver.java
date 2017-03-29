package com.example.kimhao.first_project.Alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.kimhao.first_project.R;

/**
 * Created by KimHao on 28/03/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("mAlarmReceiver","receiver an intent"+intent.getAction());
        if (intent.getAction().equals(AlarmActivity.ACTION)){
            int notiID = intent.getExtras().getInt("NotifID");

            NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

            Intent notifIntent = new Intent(context,AlarmActivity.class);
            notifIntent.putExtra("mytype","2 minutes later?");

            PendingIntent pendingIntent = PendingIntent.getActivity(context,notiID,notifIntent,0);

            Notification notification = new NotificationCompat.Builder(context)
                    .setSmallIcon(R.drawable.ic_home_red_700_24dp)
                    .setWhen(System.currentTimeMillis())
                    .setContentTitle("Time's up")
                    .setContentText("This is your alert, courtesy of the AlertManager")
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
                    .build();

            nm.notify(notiID,notification);
        }
    }
}
