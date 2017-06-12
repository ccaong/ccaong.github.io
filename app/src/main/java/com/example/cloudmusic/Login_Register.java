package com.example.cloudmusic;

import android.app.Activity;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by 聪 on 2017/6/6.
 */

public class Login_Register extends AppCompatActivity {
    ImageButton btn_login,btn_register,btn_youke;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.layout_login_register);

        btn_login= (ImageButton) findViewById(R.id.imageButton3);
        btn_register = (ImageButton) findViewById(R.id.imageButton4);
        btn_youke= (ImageButton) findViewById(R.id.imageButton);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(Login_Register.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(Login_Register.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        btn_youke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  =new Intent(Login_Register.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
