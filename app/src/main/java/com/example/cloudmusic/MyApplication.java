package com.example.cloudmusic;

import android.app.Application;
import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 聪 on 2017/6/20.
 */

public class MyApplication extends Application {
    public static String UserName;
    public static String Url="http://10.18.31.152:8080/";
    public static List<Music> musicList;

    @Override
    public void onCreate() {
        super.onCreate();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,null,null,null,null);
        musicList = new ArrayList<>();
        while (cursor.moveToNext()){
            Music music = new Music();
            music.setName(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE)));
            music.setArtist(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST)));
            music.setDuration(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION)));
            music.setPath(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
            musicList.add(music);
        }
        cursor.close();
    }
}