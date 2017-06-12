package com.example.cloudmusic;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity {
    //抽屉菜单
    private DrawerLayout drawerLayout;
    private RelativeLayout leftLayout;
    private ViewPager mViewPager;
    private FragmentPagerAdapter mAdapter;
    private LinearLayout mTabMusic,mTabMain,mTabNews;
    private ImageButton imgMusic,imgMain,imgNews,imgFindMusic,imgmine;

    private WebView webview;

    private TextView tv_name,tv_login;
    private Button btn_login, btn_qd;
    private String str1;
    private ImageView img_user;
    private Button btn_start;


//    @Override
//    protected void onStart() {
//        super.onStart();
//        //获取上一个活动传递来的信息
//        str1 = getIntent().getStringExtra("param1");
//
//        if(str1==null){
//            btn_qd.setVisibility(View.INVISIBLE);//签到按钮处于不可见状态
//            img_user.setVisibility(View.INVISIBLE);
//            tv_name.setVisibility(View.INVISIBLE);
//
//        }else {
//
//            Log.i("ccaong",str1);
//            btn_qd.setVisibility(View.VISIBLE);//签到按钮处于可见状态
//            img_user.setVisibility(View.VISIBLE);
//            tv_name.setVisibility(View.VISIBLE);
//
//            btn_login.setVisibility(View.INVISIBLE);
//            tv_login.setVisibility(View.INVISIBLE);
//            tv_name.setText(str1);//将获取的用户名赋值给控件
//        }
//    }



    @Override
    protected void onResume() {

        super.onResume();

        //获取上一个活动传递来的信息
        str1 = getIntent().getStringExtra("param1");

        if(str1==null){
            btn_qd.setVisibility(View.INVISIBLE);//签到按钮处于不可见状态
            img_user.setVisibility(View.INVISIBLE);
            tv_name.setVisibility(View.INVISIBLE);

        }else {

            Log.i("ccaong",str1);
            btn_qd.setVisibility(View.VISIBLE);//签到按钮处于可见状态
            img_user.setVisibility(View.VISIBLE);
            tv_name.setVisibility(View.VISIBLE);

            btn_login.setVisibility(View.INVISIBLE);
            tv_login.setVisibility(View.INVISIBLE);
            tv_name.setText(str1);//将获取的用户名赋值给控件
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SDK版本21以上，状态栏操作：1全屏显示，且状态栏被隐藏覆盖掉；2防止系统栏隐藏时内容区域大小发生变化
//        if (Build.VERSION.SDK_INT >= 21) {
//            View decorView = getWindow().getDecorView();
//            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
//            decorView.setSystemUiVisibility(option);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //抽屉菜单
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        leftLayout=(RelativeLayout) findViewById(R.id.left);

        initViews();
        initEvents();
        initDatas();

    }




    View.OnClickListener onClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //重置所有图片
            resetImgs();
            //更换图片
            //获取点击的id
            switch (v.getId()){
                case R.id.tab_music:
                    selectTab(0);
                    break;
                case R.id.tab_main:
                    selectTab(1);
                    break;
                case R.id.tab_news:
                    selectTab(2);
                    break;
            }
        }
    };

    //找到控件
    private void initViews(){
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mTabMusic = (LinearLayout) findViewById(R.id.tab_music);
        mTabMain = (LinearLayout) findViewById(R.id.tab_main);
        mTabNews = (LinearLayout) findViewById(R.id.tab_news);

        imgMusic= (ImageButton) findViewById(R.id.img_music);
        imgMain= (ImageButton) findViewById(R.id.img_main);
        imgNews= (ImageButton) findViewById(R.id.img_news);

        btn_start= (Button) findViewById(R.id.login);

        imgFindMusic= (ImageButton) findViewById(R.id.img_findmusic);

        btn_login= (Button) findViewById(R.id.login);
        btn_qd= (Button) findViewById(R.id.btn_qd);
        img_user= (ImageView) findViewById(R.id.img_user);
        tv_name= (TextView) findViewById(R.id.tv_name);
        tv_login= (TextView) findViewById(R.id.tv_login);

        imgmine= (ImageButton) findViewById(R.id.img_mine);
    }

    //点击事件
    private void initEvents(){
        mTabMusic.setOnClickListener(onClickListener);
        mTabMain.setOnClickListener(onClickListener);
        mTabNews.setOnClickListener(onClickListener);

        //打开登录&注册界面
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this,Login_Register.class);
                startActivity(intent);
            }
        });

        //查找音乐界面
        imgFindMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FindMusic.class);
                startActivity(intent);
            }
        });

    }
    //初始化数据
    private void initDatas(){
        //创建一个动态数组
        ViewPager child_viewpager;
        LayoutInflater inflater = LayoutInflater.from(this);
        List<View> viewList=null,childlist =null;
        View childViewas = null,childViewml = null,childViewpr = null,childViewrl=null,fmaview=null,fmuview=null,fneview=null;
        viewList=new ArrayList<View>();
        childlist =new ArrayList<View>();
        LayoutMain viewmain;

            fmaview = inflater.inflate(R.layout.fragmentmain, null);
            fmuview=inflater.inflate(R.layout.fragmentmusic,null);
            fneview=inflater.inflate(R.layout.fragmentnews,null);
            viewmain = (LayoutMain) fmaview.findViewById(R.id.viewmain);
            viewList.add(fmuview);
            viewList.add(fmaview);
            viewList.add(fneview);

            child_viewpager = (ViewPager) fmaview.findViewById(R.id.child_viewpager);
            //注入里层viewpager
            viewmain.setChild_viewpager(child_viewpager);

            childlist = new ArrayList<View>();

                childViewas = inflater.inflate(R.layout.fgastation, null);
                childViewml = inflater.inflate(R.layout.fgmlist, null);
                childViewpr = inflater.inflate(R.layout.fgprecommend, null);
                childViewrl = inflater.inflate(R.layout.fgrlist, null);
                childlist.add(childViewas);
                childlist.add(childViewml);
                childlist.add(childViewpr);
                childlist.add(childViewrl);

            child_viewpager.setAdapter(new ViewPagerAdapter(childlist));

            mViewPager.setAdapter(new ViewPagerAdapter(viewList));

        /*mFragments.add(new FragmentMusic());
        mFragments.add(new FragmentMain());
        mFragments.add(new FragmentNews());*/

        /*mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }
            @Override
            public Fragment getItem(int position) {
                return  mFragments.get(position);
            }
        };*/
        //mViewPager.setAdapter(mAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    mViewPager.setCurrentItem(position);
                }
                resetImgs();
                selectTab(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //设置ViewPager的适配器
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            mViewPager.setAdapter(mAdapter);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                }
                @Override
                public void onPageSelected(int position) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                        mViewPager.setCurrentItem(position);
                    }
                    resetImgs();
                    selectTab(position);
                }
                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });
        }*/
    }
    private class ViewPagerAdapter extends PagerAdapter {
        List<View> viewLists;
        public ViewPagerAdapter(List<View> list) {
            viewLists=list;
        }

        @Override
        public int getCount() {
            return viewLists.size();
        }
        //是否由对象生成界面
        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
        //自动方法删除页
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewLists.get(position));
        }
        //确定viewpager的item对象
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewLists.get(position),0);

            return viewLists.get(position);
        }
    }
    //根据选择更换图片
    private void selectTab(int i){
        switch (i){
            case 0:
                imgMusic.setImageResource(R.mipmap.music1);
                break;
            case 1:
                imgMain.setImageResource(R.mipmap.main1);
                break;
            case 2:
                imgNews.setImageResource(R.mipmap.imgnew1);
                break;
        }
        mViewPager.setCurrentItem(i);
    }

    //重置所有的图片
    private void resetImgs(){
        imgMusic.setImageResource(R.mipmap.music);
        imgMain.setImageResource(R.mipmap.main);
        imgNews.setImageResource(R.mipmap.imgnew);
    }
    //musicview更换图片
    private void reEvent(int i){
        switch (i){
            case 0:
                imgMusic.setImageResource(R.mipmap.music1);
                break;
            case 1:
                imgMain.setImageResource(R.mipmap.main1);
                break;
            case 2:
                imgNews.setImageResource(R.mipmap.imgnew1);
                break;
        }
        mViewPager.setCurrentItem(i);
    }
    //退出事件
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK){
            //调用自定义的exit方法
            exit();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private long checkTime =0;
    private void exit() {
        if((System.currentTimeMillis()-checkTime)>2000){
            Toast.makeText(MainActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();
            checkTime= System.currentTimeMillis();
        }else{
            this.finish();
        }
    }
}
