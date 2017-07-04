package com.example.cloudmusic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by lsadmin on 2017/6/6.
 */
//个性推荐
public class FgPersonRecommend extends Fragment{
    Button btn;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.fgprecommend,null);
        btn = (Button) v.findViewById(R.id.btn_java);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FgPersonRecommend.super.getActivity(),"2333333",Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }
}