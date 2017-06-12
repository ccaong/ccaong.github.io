package com.example.cloudmusic;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


/**
 * Created by 聪 on 2017/6/6.
 */

public class RegisterActivity extends AppCompatActivity {
    private EditText tv_phone,tv_pwd;
    private ImageButton img_back;
    private Button btn_register;
    RequestQueue queue;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);

        //隐藏actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        img_back= (ImageButton) findViewById(R.id.img_register_back);
        tv_phone= (EditText) findViewById(R.id.editText3);
        tv_pwd= (EditText) findViewById(R.id.editText4);
        btn_register= (Button) findViewById(R.id.button5);

        queue = Volley.newRequestQueue(this);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,Login_Register.class);
                startActivity(intent);
            }
        });
    }

    private void register() {
        String username=tv_phone.getText().toString();
        String password=tv_pwd.getText().toString();
        String url="http://10.18.31.152:8080/MusicDemo1/reg?username="+ username+"&password="+password;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                Log.i("request", "请求成功：" + s);
                if(s.equals("true")){
                    Intent intent  = new Intent(RegisterActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    AlertDialog.Builder adb= new AlertDialog.Builder(RegisterActivity.this);
                    adb.setTitle("提示");
                    adb.setMessage("该用户已经存在，是否登录");

                    adb.setNegativeButton("登录", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent  =new Intent(RegisterActivity.this,LoginActivity.class);
                            startActivity(intent);
                        }
                    });
                    adb.setPositiveButton("注册", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    });
                    adb.create();
                    adb.show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("request","请求失败");
                Toast.makeText(RegisterActivity.this,"与服务器断开连接，请稍后重试！",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(stringRequest);
    }
}
