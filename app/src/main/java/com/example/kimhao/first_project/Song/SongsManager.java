package com.example.kimhao.first_project.Song;

import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by KimHao on 19/03/2017.
 */

public class SongsManager {
    //SDCard Path
    final String MEDIA_PATH = new String("/Music/");
    private ArrayList<Song> songsList  = new ArrayList<Song>();

    //Constructor
    public SongsManager(){

    }

    public ArrayList<Song> getListSongOffline(){
        File home = new File(MEDIA_PATH);
        File[] files = home.listFiles(new FileExtentionFilter());
        Log.d("tôi muốn bik", "getListSongOffline: "+home.listFiles(new FileExtentionFilter()));

        //if (home.listFiles(new FileExtentionFilter()).length != 0){
        if (null != files && files.length >0){
            for (File file : home.listFiles(new FileExtentionFilter())){
                Song song = new Song();
                song.setTitle(file.getName().substring(0,(file.getName().length()-4)));
                song.setPath(file.getPath());

                songsList.add(song);
            }
        }

        //return songs list array
        Collections.sort(songsList, new Comparator<Song>() {
            @Override
            public int compare(Song o1, Song o2) {
                return o1.getTitle().compareTo(o2.getTitle());
            }
        });
        return songsList;
    }

    //create class FileExtentionFilter
    class FileExtentionFilter implements FilenameFilter{
        @Override
        public boolean accept(File dir, String name) {
            return (name.endsWith(".mp3") || name.endsWith(".MP3"));
        }
    }
}
