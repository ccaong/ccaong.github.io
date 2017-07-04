package com.example.cloudmusic;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    //抽屉菜单
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    private ViewPager mViewPager;

    private LinearLayout mTabMusic,mTabMain,mTabNews;
    private ImageButton imgMusic,imgMain,imgNews,imgFindMusic,imgmine;

    private TextView tv_username,tv_ts;
    private Button btn_login, btn_qd,btn_play;
    private ImageView img_user;

    private String str1;            //存储全局变量信息（用户名）


    private FragmentPagerAdapter mAdapter;

//    private List<Fragment> mFragments;
//    public ViewPager child_viewpager;


    //根据登录状态显示不同的信息
    @Override
    protected void onResume() {

        super.onResume();

        //获取全局变量的信息
        str1 = MyApplication.UserName;
        if(str1==null){
            btn_qd.setVisibility(View.INVISIBLE);//签到按钮处于不可见状态
            img_user.setVisibility(View.INVISIBLE);
            tv_username.setVisibility(View.INVISIBLE);

        }else {
            btn_qd.setVisibility(View.VISIBLE);//签到按钮处于可见状态
            img_user.setVisibility(View.VISIBLE);
            tv_username.setVisibility(View.VISIBLE);

            btn_login.setVisibility(View.GONE);
            tv_ts.setVisibility(View.GONE);
            tv_username.setText(str1);//将获取的用户名赋值给控件
        }

        //根据是否播放决定是否显示底部的按钮
        if(PlayService.mediaPlayer != null && PlayService.mediaPlayer.isPlaying()){
            btn_play.setVisibility(View.VISIBLE);
        }else {
            btn_play.setVisibility(View.GONE);
        }

        //显示正在播放的歌曲名
        btn_play.setText("正在播放："+MyApplication.MusicName+">>>");
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

        //隐藏actionBar

        //隐藏actionBar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        initViews();        //寻找控件方法
        initEvents();       //设置监听、点击事件方法
        initDatas();        //初始化数据方法

        //设置主页面显示第几个fragment界面
        mViewPager.setCurrentItem(1);

        //设置抽屉菜单中默认选中项目
        navigationView.setCheckedItem(R.id.nav_call);

    }

    //找到控件
    private void initViews(){

        //抽屉菜单
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);      //布局文件
        navigationView= (NavigationView) findViewById(R.id.nav_view);

        View headerView = navigationView.getHeaderView(0);
        //抽屉菜单内的控件
        tv_ts= (TextView)  headerView.findViewById(R.id.tv_ts);               //登录提示
        btn_login= (Button) headerView.findViewById(R.id.btn_login);         //登录按钮
        btn_qd= (Button) headerView.findViewById(R.id.btn_qd);                //签到按钮
        img_user= (ImageView)  headerView.findViewById(R.id.icon_image);     //用户头像
        tv_username= (TextView)  headerView.findViewById(R.id.tv_username);  //用户名

        //主界面中的viewpager
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        //主界面的三个主要控件
        mTabMusic = (LinearLayout) findViewById(R.id.tab_music);
        mTabMain = (LinearLayout) findViewById(R.id.tab_main);
        mTabNews = (LinearLayout) findViewById(R.id.tab_news);

        //主界面的三个主要控件
        imgMusic= (ImageButton) findViewById(R.id.img_music);
        imgMain= (ImageButton) findViewById(R.id.img_main);
        imgNews= (ImageButton) findViewById(R.id.img_news);

        //左右两侧的图片按钮
        imgFindMusic= (ImageButton) findViewById(R.id.img_findmusic);
        imgmine= (ImageButton) findViewById(R.id.img_mine);

        //播放时显示的一个按钮
        btn_play= (Button) findViewById(R.id.play1);

    }

    //点击事件
    private void initEvents(){
        mTabMusic.setOnClickListener(onClickListener);
        mTabMain.setOnClickListener(onClickListener);
        mTabNews.setOnClickListener(onClickListener);

        //查找音乐界面
        imgFindMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,FindMusic.class);
                startActivity(intent);
            }
        });

        //点击展开抽屉菜单
        imgmine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        //抽屉菜单中的选择项的点击事件
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){
            @Override
            public boolean onNavigationItemSelected(MenuItem item){
                drawerLayout.closeDrawers();
                return true;
            }
        });

        //签到按钮点击事件
        btn_qd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"签到成功",Toast.LENGTH_SHORT).show();
                btn_qd.setText("已签到");
            }
        });

        //打开登录&注册界面
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(MainActivity.this,Login_Register.class);
                startActivity(intent);
                drawerLayout.closeDrawers();
            }
        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MusicPlayer.class);
                startActivity(intent);
            }
        });

    }

    //初始化数据
    private void initDatas(){


        //LayoutInflater用来加载布局
//        LayoutInflater inflater = LayoutInflater.from(this);
        //List<View> viewList,childlist ;
//        final List<Fragment> viewList,childlist;
//        View childViewas,childViewml,childViewpr,childViewrl,fmaview,fmuview,fneview;

//        LayoutMain viewmain;
//        fmaview = inflater.inflate(R.layout.fragmentmain, null);
//        fmuview=inflater.inflate(R.layout.fragmentmusic,null);
//        fneview=inflater.inflate(R.layout.fragmentnews,null);
//        viewmain = (LayoutMain) fmaview.findViewById(R.id.viewmain);


//            child_viewpager = (ViewPager) fmaview.findViewById(R.id.child_viewpager);
//
//            //注入里层viewpager
//            viewmain.setChild_viewpager(child_viewpager);
//
//            childlist = new ArrayList<>();
//
//               /* childViewas = inflater.inflate(R.layout.fgastation,null);
//                childViewml = inflater.inflate(R.layout.fgmlist, null);
//                childViewpr = inflater.inflate(R.layout.fgprecommend, null);
//                childViewrl = inflater.inflate(R.layout.fgrlist, null);*/
//                childlist.add(new FgAnchorStation());
//                childlist.add(new FgMusicList());
//                childlist.add(new FgPersonRecommend());
//                childlist.add(new FgRankList());
//
//            child_viewpager.setAdapter(mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
//
//                @Override
//                public int getCount() {
//                    return childlist.size();
//                }
//                @Override
//                public Fragment getItem(int position) {
//                    return  childlist.get(position);
//                }
//            });
//            child_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//                @Override
//                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//                }
//
//                @Override
//                public void onPageSelected(int position) {
//                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
//                        child_viewpager.setCurrentItem(position);
//                    }
//                }
//
//                @Override
//                public void onPageScrollStateChanged(int state) {
//
//                }
//            });

        final List<Fragment> viewList=new ArrayList<>();
        viewList.add(new FragmentMusic());
        viewList.add(new FragmentMain());
        viewList.add(new FragmentNews());

        mViewPager.setAdapter(mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return viewList.size();
            }
            @Override
            public Fragment getItem(int position) {
                return  viewList.get(position);
            }
        });

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
    }



    //点击事件
    View.OnClickListener onClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //重置所有图片
            resetImgs();
            //根据点击的id更换图片
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

    //重置所有的图片
    private void resetImgs(){
        imgMusic.setImageResource(R.mipmap.music);
        imgMain.setImageResource(R.mipmap.main);
        imgNews.setImageResource(R.mipmap.imgnew);
    }

    //根据选择更换图片，更换显示第几个fragment
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
    //双击退出
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
