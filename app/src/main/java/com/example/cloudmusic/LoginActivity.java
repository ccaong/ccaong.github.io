package com.example.cloudmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;


/**
 * Created by 聪 on 2017/6/6.
 */

public class LoginActivity extends AppCompatActivity {
    private EditText tv_phone,tv_pwd;
    private ImageButton img_back;
    private Button btn_login;
    private String data1;
    private ProgressBar progressBar;
    RequestQueue queue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);

        //隐藏actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tv_phone= (EditText) findViewById(R.id.editText);
        tv_pwd= (EditText) findViewById(R.id.editText2);
        btn_login= (Button) findViewById(R.id.button2);
        img_back= (ImageButton) findViewById(R.id.img_login_back);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);

        //隐藏progressBar
        progressBar.setVisibility(View.INVISIBLE);

        queue = Volley.newRequestQueue(this);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //显示progressBar
                progressBar.setVisibility(View.VISIBLE);
                login();
            }
        });

        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,Login_Register.class);
                startActivity(intent);
            }
        });

    }
    private void login() {

        btn_login.setText("正在登录....");

        String username=tv_phone.getText().toString();
        String password=tv_pwd.getText().toString();

        String url= MyApplication.Url+"MusicLogin/login?username="+ username+"&password="+password;
        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                Log.i("request", "请求成功：" + s);
                if(s.equals("success")){

                    String data1=tv_phone.getText().toString();
                    MyApplication.UserName = data1;
                    Intent intent  = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

//                    //隐藏progressBar
                    progressBar.setVisibility(View.GONE);
                }else{
                    Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新输入！",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Log.i("request","请求失败");
               Toast.makeText(LoginActivity.this,"与服务器断开连接，请稍后重试！",Toast.LENGTH_SHORT).show();
                btn_login.setText("登录");

                //隐藏progressBar
                    progressBar.setVisibility(View.GONE);
            }
        });
        queue.add(stringRequest);
    }
}
