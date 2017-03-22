package com.example.kimhao.first_project.Song;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.kimhao.firstproject.R;

import java.util.ArrayList;

/**
 * Created by KimHao on 19/03/2017.
 */

public class SongsAdapter extends BaseAdapter {
    private ArrayList<Song> songs;
    private LayoutInflater songInf;

    public SongsAdapter(Context c, ArrayList<Song> theSong) {
        this.songs = theSong;
        this.songInf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //map to song layout
        LinearLayout songLay = (LinearLayout) songInf.inflate(R.layout.item_song,parent,false);

        //get title and artist views
        TextView tvSongTitle = (TextView)songLay.findViewById(R.id.tvSong_title);
        TextView tvSongArtist = (TextView)songLay.findViewById(R.id.tvSong_artist);

        //get song using position
        Song currSong = songs.get(position);
        //get title and artist strings
        tvSongTitle.setText(currSong.getTitle());
        tvSongTitle.setText(currSong.getArtist());

        //set positon as tag
        songLay.setTag(position);
        return songLay;
    }

}
