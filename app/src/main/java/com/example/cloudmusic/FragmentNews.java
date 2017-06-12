package com.example.cloudmusic;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by 聪 on 2017/4/20.
 */

public class FragmentNews extends Fragment {
    private WebView webview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentnews, null);

        //实例化WebView对象
//        webview = new WebView(this);
//        //设置WebView属性，能够执行Javascript脚本
//        webview.getSettings().setJavaScriptEnabled(true);
//        webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//        webview.loadUrl("http://www.baidu.com");
//        //设置Web视图
//        setContentView(webview);
        return v;
    }

}