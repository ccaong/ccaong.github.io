package com.example.cloudmusic;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;

import java.io.IOException;

/**
 * Created by hongweikeji on 2017/6/5.
 */

public class PlayService extends Service {
    public static MediaPlayer mediaPlayer;
    public static int current;

    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        mediaPlayer = new MediaPlayer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        current = intent.getIntExtra("pos", 0);
        play(current);

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.reset();
                play(++current % MyApplication.musicList.size());
            }
        });

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }

    public void play(int pos) {
        Music music = MyApplication.musicList.get(pos);
        Notification.Builder builder = new Notification.Builder(PlayService.this);
        builder.setSmallIcon(R.mipmap.ic_launcher).
                setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)).
                setContentTitle ("播放").setContentText("正在播放" + music.getName());

        Notification notification = builder.build();

        startForeground(1, notification);

        mediaPlayer.reset();

        try {
            mediaPlayer.setDataSource(music.getPath());
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalStateException ex) {
            ex.printStackTrace();
        }

    }
}