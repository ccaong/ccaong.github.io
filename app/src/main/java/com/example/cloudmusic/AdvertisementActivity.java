package com.example.cloudmusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by 聪 on 2017/5/11.
 */

public class AdvertisementActivity extends Activity {
    private final long SPLASH_LENGTH = 2000;
            Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_advertisement);
        handler.postDelayed(new Runnable() {  //使用handler的postDelayed实现延时跳转
            public void run() {
                                Intent intent = new Intent(AdvertisementActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            }
                    }, SPLASH_LENGTH);//2秒后跳转至应用主界面MainActivity
    }
}
