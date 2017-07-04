package com.example.cloudmusic;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 聪 on 2017/4/20.
 */

public class FragmentMain extends Fragment {
    ViewPager child_viewpager;
//    List<View> childlist;
    Button btn;
    private TextView tv_gxtj,tv_gd,tv_zbdt,tv_phb;

    LayoutMain viewmain;
    ArrayList<Object> childlist;
    FragmentPagerAdapter mAdapter;

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragmentmain,null);
        child_viewpager = (ViewPager) v.findViewById(R.id.child_viewpager);

        tv_gxtj = (TextView) v.findViewById(R.id.tv_gxtj);
        tv_gd = (TextView) v.findViewById(R.id.tv_gd);
        tv_zbdt = (TextView) v.findViewById(R.id.tv_zbdt);
        tv_phb = (TextView) v.findViewById(R.id.tv_phb);

        tv_gxtj.setOnClickListener(onClickListener);
        tv_gd.setOnClickListener(onClickListener);
        tv_zbdt.setOnClickListener(onClickListener);
        tv_phb.setOnClickListener(onClickListener);

        //注入里层viewpager
        childlist = new ArrayList<>();

//        childlist.add(inflater.inflate(R.layout.fgprecommend,null));
//        childlist.add(inflater.inflate(R.layout.fgrlist,null));
//        childlist.add(inflater.inflate(R.layout.fgmlist,null));
//        childlist.add(inflater.inflate(R.layout.fgastation,null));
//        child_viewpager.setAdapter(new ViewPagerAdapter(childlist));


        childlist.add(new FgPersonRecommend());
        childlist.add(new FgAnchorStation());
        childlist.add(new FgMusicList());
        childlist.add(new FgRankList());

        child_viewpager.setAdapter(mAdapter = new FragmentPagerAdapter(getChildFragmentManager()) {

            @Override
            public int getCount() {
                return childlist.size();
            }
            @Override
            public Fragment getItem(int position) {
                return (Fragment) childlist.get(position);
            }
        });

        //默认显示页
        child_viewpager.setCurrentItem(0);

        child_viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
                    child_viewpager.setCurrentItem(position);
                }
                child_viewpager.setCurrentItem(position);
                resetColor();
                selectColor(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        return v;
    }

    View.OnClickListener onClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            //重置所有颜色
            resetColor();
            //根据点击的id更换颜色
            switch (v.getId()){
                case R.id.tv_gxtj:
                    selectColor(0);
                    break;
                case R.id.tv_gd:
                    selectColor(1);
                    break;
                case R.id.tv_zbdt:
                    selectColor(2);
                    break;
                case R.id.tv_phb:
                    selectColor(3);
                    break;
            }
        }
    };

    //重置所有文字的颜色
    private void resetColor(){
        tv_gxtj.setTextColor(Color.rgb(105,105,105));
        tv_gd.setTextColor(Color.rgb(105,105,105));
        tv_zbdt.setTextColor(Color.rgb(105,105,105));
        tv_phb.setTextColor(Color.rgb(105,105,105));
    }
    //根据选择，设置字体的颜色
    private void selectColor(int i){
        switch (i){
            case 0:
                tv_gxtj.setTextColor(Color.rgb(211,58,49));
                break;
            case 1:
                tv_gd.setTextColor(Color.rgb(211,58,49));
                break;
            case 2:
                tv_zbdt.setTextColor(Color.rgb(211,58,49));
                break;
            case 3:
                tv_phb.setTextColor(Color.rgb(211,58,49));
                break;
        }
        child_viewpager.setCurrentItem(i);
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
}