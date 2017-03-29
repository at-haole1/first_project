package com.example.kimhao.first_project.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by KimHao on 29/03/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    public static final String ADD_TEXT = "addtext";
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context,SendingActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (i.getAction().equals(ADD_TEXT)){
            i.putExtra("MyKey","This is second my text to send using my key");

        }
    }
}
