package com.example.kimhao.first_project.Song;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;


import com.example.kimhao.first_project.R;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by KimHao on 20/03/2017.
 */

public class MusicService extends Service implements MediaPlayer.OnPreparedListener,
        MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener{
    BroadcastReceiver mReceiver;
    public static final int NOTIIFCATION_ID = 1;
    //media player
    private MediaPlayer player;
    //song list
    private ArrayList<Song> songs;
    //current position
    private int songPos;

    private final IBinder musicBinder = new MusicBinder();

    public void onCreate(){
        //create the service
        super.onCreate();
        //initialize position
        songPos = 0;
        //create player
        player = new MediaPlayer();
        initMusicPlayer();
        setList(new SongsManager().getListSongOffline());
        IntentFilter filter = new IntentFilter();
        filter.addAction(SongActivity.ACTION_PLAY);
        mReceiver = new MyReceiver();
        registerReceiver(mReceiver,filter);

    }

    public void initMusicPlayer(){
        player.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        player.setAudioStreamType(AudioManager.STREAM_MUSIC);

        player.setOnPreparedListener(this);
        player.setOnErrorListener(this);
        player.setOnCompletionListener(this);


    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return musicBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        player.stop();
        player.release();
        return false;
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //start playback
        mp.start();
    }

    public class MusicBinder extends Binder {
        MusicService getService(){
            return MusicService.this;
        }
    }

    public void setList(ArrayList<Song> theSongs){
        songs = theSongs;
    }

    public void setSong(int songIndex){
        songPos = songIndex;
    }
    public class MyReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(SongActivity.ACTION_PLAY)){
                setSong(intent.getIntExtra(SongActivity.INDEX_MP3,0));
                playSong();
            }
        }
    }

    public void playSong() {
        Song playSong = songs.get(songPos);
        try{
            player.reset();
            player.setDataSource(playSong.getPath());
            player.prepare();
            createNotification(playSong.getTitle());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createNotification(String songName){
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.icon_song);
        builder.setContentTitle(songName);
        builder.setTicker("play "+songName);
        final Intent notificationIntent = new Intent(this,SongActivity.class);
        final PendingIntent pi = PendingIntent.getActivities(this,0, new Intent[]{notificationIntent},0);
        builder.setContentIntent(pi);
        final Notification notification = builder.build();
        startForeground(NOTIIFCATION_ID,notification);
    }

}
