package com.example.kimhao.first_project.Song;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.example.kimhao.firstproject.R;

import java.util.ArrayList;

public class SongActivity extends AppCompatActivity {
    public static final String ACTION_PLAY = "action_play";
    public static final String INDEX_MP3 = "index_mp3";

    private ArrayList<Song> mSongsList;
    private ListView mLvSong;
    private MusicService mMusicService;
    private Intent mPlayIntent;
    private boolean mMusicBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mLvSong = (ListView) findViewById(R.id.lvSongList);
        mSongsList = new ArrayList<Song>();
        getSongList();
        Log.d("Tôi muốn biết", "onCreate: "+mSongsList.size()+"");
        if (mPlayIntent == null){
            mPlayIntent = new Intent(SongActivity.this, MusicService.class);
            startActivity(mPlayIntent);
        }

        SongsAdapter songsAdapter = new SongsAdapter(SongActivity.this,mSongsList);
        mLvSong.setAdapter(songsAdapter);
    }

    public void songPicker(View view){
        Intent intent = new Intent(ACTION_PLAY);
        intent.putExtra(INDEX_MP3,Integer.parseInt(view.getTag().toString()));
        sendBroadcast(intent);
    }
    public void getSongList() {
        mSongsList = new SongsManager().getListSongOffline();
    }

    //create menu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_song,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_shuffle:
                break;
            case R.id.action_end:
                stopService(mPlayIntent);
                mMusicService = null;
                System.exit(0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
