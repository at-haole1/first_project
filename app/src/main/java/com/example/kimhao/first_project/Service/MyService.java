package com.example.kimhao.first_project.Service;

/*
 * Created by KimHao on 28/03/2017.
 */

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;

import com.example.kimhao.first_project.R;

public class MyService extends Service {
    MediaPlayer play;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate() {
        super.onCreate();
        play = MediaPlayer.create(getApplicationContext(), R.raw.c);
        Toast.makeText(getApplicationContext(), "Đang nghe nhạc",Toast.LENGTH_LONG).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        play.start();
        return START_STICKY;
    }


    @Override
    public void onDestroy() {
        play.release();
        Toast.makeText(getApplicationContext(),"Dừng nhạc",Toast.LENGTH_LONG).show();
        super.onDestroy();

    }
}
