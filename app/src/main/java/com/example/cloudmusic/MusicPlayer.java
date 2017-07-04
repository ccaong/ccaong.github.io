package com.example.cloudmusic;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
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
    private ImageButton img_back;
    private SeekBar sbr;
    private boolean isSeekBarChanging;//互斥变量，防止进度条与定时器冲突。



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.musicplayer);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        btn_start= (Button) findViewById(R.id.btn_startmusic);
        btn_pre= (Button) findViewById(R.id.btn_pre);
        btn_next= (Button) findViewById(R.id.btn_next);

        img_back = (ImageButton) findViewById(R.id.img_back);

        tv_musicname= (TextView) findViewById(R.id.tv_top);

        sbr= (SeekBar) findViewById(R.id.seekBar);
        sbr.setOnSeekBarChangeListener(new MySeekBar());



        //返回按钮
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MusicPlayer.this,LocalMusic.class);
                startActivity(intent);
            }
        });

        current=getIntent().getIntExtra("pos",0);
//
//        handler.postDelayed(new Runnable() {  //使用handler的postDelayed实现延时跳转
//            public void run() {
//                Intent intent = new Intent(MusicPlayer.this,PlayService.class);
//                intent.putExtra("pos",current);
//                startService(intent);
//            }
//        }, SPLASH_LENGTH);//启动服务
//
//        Music music = MyApplication.musicList.get(current);
//        mname=music.getName();
//        mauthor=music.getArtist();

        tv_musicname.setText(MyApplication.MusicName);

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
                if((current-1)>=0){
                    Intent intent = new Intent(MusicPlayer.this,PlayService.class);
                    intent.putExtra("pos",current--);
                    startService(intent);
                }else{
                    Toast.makeText(MusicPlayer.this,"已经是第一首了",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if((current+1)<MyApplication.musicList.size()){
                    Intent intent = new Intent(MusicPlayer.this,PlayService.class);
                    intent.putExtra("pos",current++);
                    startService(intent);
                }else{
                    Toast.makeText(MusicPlayer.this,"已经是最后一首了",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    /*进度条处理*/
    public class MySeekBar implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
        }

        /*滚动时,应当暂停后台定时器*/
        public void onStartTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = true;
        }
        /*滑动结束后，重新设置值*/
        public void onStopTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = false;
            PlayService.mediaPlayer.seekTo(seekBar.getProgress());
        }
    }

    protected void onDestroy() {
        super.onDestroy();
    }

}

