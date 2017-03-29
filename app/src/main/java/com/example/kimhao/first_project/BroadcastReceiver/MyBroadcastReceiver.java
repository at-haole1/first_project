package com.example.kimhao.first_project.BroadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by KimHao on 28/03/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {

    public MyBroadcastReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.v("myReceiver","receiver an intent");
        Intent i = new Intent(context, BroadcastReceiverActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        if (intent.getAction().equals(Intent.ACTION_BATTERY_LOW)){
            Log.v("myReceiver","battery low");
            i.putExtra("mStatus",1);
            context.startActivity(i);
        }else if(intent.getAction().equals(Intent.ACTION_BATTERY_OKAY)){
            Log.v("myReceiver","battery okay");
            i.putExtra("mStatus",2);
            context.startActivity(i);
        }else if (intent.getAction().equals(Intent.ACTION_POWER_CONNECTED)){
            Log.v("mReceiver","ac on");
            i.putExtra("mStatus",3);
            context.startActivity(i);
        }else if (intent.getAction().equals(Intent.ACTION_POWER_DISCONNECTED)){
            Log.v("mReceiver","ac off");
            i.putExtra("mStatus",4);
            context.startActivity(i);
        }

    }
}
