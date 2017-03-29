package com.example.kimhao.first_project.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.kimhao.first_project.R;

/**
 * Created by KimHao on 28/03/2017.
 */

public class AlarmActivity extends AppCompatActivity {
    public static final String ACTION = "notif";

    AlarmFragment mFragment = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_broadcastnoti);

        String info = "Nothing";
        Bundle extras = getIntent().getExtras();
        Log.d("extras", "onCreate: "+extras);
        if (extras != null){
            info = extras.getString("mytype");
            Log.d("info", "onCreate: "+info);
            if (info == null){
                info = "still nothing";
            }
        }
        //Log.d("info ", "info: "+info);

        mFragment = new AlarmFragment(info);
        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.containerNoti,mFragment).commit();
        }
    }

    public void setalarm(){
        int NotID = 1;

        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE)+2);
        calendar.set(Calendar.SECOND,0);

        Intent notificationIntent = new Intent(AlarmActivity.ACTION);
        //Intent notificationIntent = new Intent(getApplicationContext(), AlarmReceiver.class);
        notificationIntent.putExtra("NotifID",NotID);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this,NotID,notificationIntent,0);
        Log.d("Alarm Activity", "setalarm: ");
        alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
        sendBroadcast(notificationIntent);
        finish();
    }
}
