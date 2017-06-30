package com.example.cloudmusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

/**
 * Created by 聪 on 2017/6/21.
 */

public class MusicPlayer extends AppCompatActivity {
    private final long SPLASH_LENGTH = 2;
    Handler handler = new Handler();
    public static int current;
    TextView tv_musicname,tv_musicauthor;
    Button btn_start,btn_next,btn_pre;
    public String mname,mauthor;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicplayer);

        btn_start= (Button) findViewById(R.id.btn_startmusic);
        btn_pre= (Button) findViewById(R.id.btn_pre);
        btn_next= (Button) findViewById(R.id.btn_next);

        tv_musicname= (TextView) findViewById(R.id.music_name);
        tv_musicauthor= (TextView) findViewById(R.id.music_author);

        current=getIntent().getIntExtra("pos",0);

        handler.postDelayed(new Runnable() {  //使用handler的postDelayed实现延时跳转
            public void run() {
                Intent intent = new Intent(MusicPlayer.this,PlayService.class);
                intent.putExtra("pos",current);
                startService(intent);
            }
        }, SPLASH_LENGTH);//启动服务

        Music music = MyApplication.musicList.get(current);
        mname=music.getName();
        mauthor=music.getArtist();
        tv_musicname.setText(mname);
        tv_musicauthor.setText(mauthor);

        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(PlayService.mediaPlayer.isPlaying()){
                    PlayService.mediaPlayer.pause();
                }else{
                    PlayService.mediaPlayer.start();
                }
            }
        });


        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    protected void onDestroy() {
        super.onDestroy();
        Intent intent = new Intent(MusicPlayer.this,PlayService.class);
        stopService(intent);
    }

}

