package com.example.cloudmusic;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 聪 on 2017/5/10.
 */

public class FindMusic extends Activity {
    @BindView(R.id.ed1)
    EditText ed1;
    @BindView(R.id.button)
    Button button;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.gs)
    Button gs;
    @BindView(R.id.bg)
    Button bg;
    @BindView(R.id.wf)
    Button wf;
    @BindView(R.id.mkxm)
    Button mkxm;
    @BindView(R.id.ch)
    Button ch;
    @BindView(R.id.cl)
    Button cl;
    @BindView(R.id.jam)
    Button jam;
    @BindView(R.id.bzy)
    Button bzy;
    @BindView(R.id.zc)
    Button zc;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_findmusic);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button, R.id.gs, R.id.bg, R.id.wf, R.id.mkxm, R.id.ch, R.id.cl, R.id.jam,R.id.zc,R.id.bzy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.button:
                break;
            case R.id.gs:
                ed1.setText(gs.getText());
                break;
            case R.id.bg:
                ed1.setText(bg.getText());
                break;
            case R.id.wf:
                ed1.setText(wf.getText());
                break;
            case R.id.mkxm:
                ed1.setText(mkxm.getText());
                break;
            case R.id.ch:
                ed1.setText(ch.getText());
                break;
            case R.id.cl:
                ed1.setText(cl.getText());
                break;
            case R.id.jam:
                ed1.setText(jam.getText());
                break;
            case R.id.zc:
                ed1.setText(zc.getText());
                break;
            case R.id.bzy:
                ed1.setText(bzy.getText());
                break;
        }
    }
}
