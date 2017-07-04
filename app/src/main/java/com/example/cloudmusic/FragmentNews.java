package com.example.cloudmusic;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by 聪 on 2017/4/20.
 */

public final class FragmentNews extends Fragment {
//    @Nullable
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.fragmentnews, null);
//
//        return v;
//    }

static WebView mWeb;
    private View mContentView;
    private static final String APP_CACAHE_DIRNAME = "/webcache";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message message) {
            switch (message.what) {
                case 1: {
                    webViewGoBack();
                }
                break;
            }
        }
    };

    private void webViewGoBack() {
        mWeb.goBack();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mContentView = inflater.inflate(R.layout.fragmentnews, null);
        mWeb = (WebView) mContentView.findViewById(R.id.webview);


        WebSettings settings = mWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        mWeb.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mWeb.getSettings().setSupportZoom(true);  //支持放大缩小
        mWeb.getSettings().setBuiltInZoomControls(true);
        mWeb.loadUrl("http://music.163.com/");
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        mWeb.getSettings().setSaveFormData(true);// 保存表单数据
        mWeb.setWebViewClient(new WebViewClient());
        String cacheDirPath = getActivity().getFilesDir().getAbsolutePath() + APP_CACAHE_DIRNAME; //缓存路径

        mWeb.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);  //缓存模式
        mWeb.getSettings().setAppCachePath(cacheDirPath); //设置缓存路径
        mWeb.getSettings().setAppCacheEnabled(true); //开启缓存功能

        mWeb.setOnKeyListener(new View.OnKeyListener() {

            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if ((keyCode == KeyEvent.KEYCODE_BACK) && mWeb.canGoBack()) {
                    handler.sendEmptyMessage(1);
                    return true;
                }
                return false;
            }

        });
        return mContentView;
    }

}