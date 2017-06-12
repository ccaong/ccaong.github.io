package com.example.cloudmusic;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lsadmin on 2017/6/6.
 */
//个性推荐
public class FgPersonRecommend extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fgprecommend,null);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
