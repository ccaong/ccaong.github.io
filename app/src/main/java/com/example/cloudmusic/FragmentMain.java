package com.example.cloudmusic;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by 聪 on 2017/4/20.
 */

public class FragmentMain extends Fragment {
    ViewPager child_viewpager;
    LayoutMain viewmain;
    ArrayList<Object> childlist;
    FragmentPagerAdapter mAdapter;
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragmentmain,null);
        child_viewpager = (ViewPager) v.findViewById(R.id.child_viewpager);

        //注入里层viewpager
        childlist = new ArrayList<>();

               /* childViewas = inflater.inflate(R.layout.fgastation,null);
                childViewml = inflater.inflate(R.layout.fgmlist, null);
                childViewpr = inflater.inflate(R.layout.fgprecommend, null);
                childViewrl = inflater.inflate(R.layout.fgrlist, null);*/

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
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        return v;
    }
}