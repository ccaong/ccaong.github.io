package com.example.cloudmusic;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 聪 on 2017/5/11.
 */

public class AdvertisementActivity extends AppCompatActivity {
    private ImageView imageView;
    private final long SPLASH_LENGTH = 2000;
            Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_advertisement);

        View decorView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(option);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        handler.postDelayed(new Runnable() {  //使用handler的postDelayed实现延时跳转
            public void run() {
                                Intent intent = new Intent(AdvertisementActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                    }, SPLASH_LENGTH);//2秒后跳转至应用主界面MainActivity
    }
}
