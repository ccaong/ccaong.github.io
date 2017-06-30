package com.example.cloudmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 聪 on 2017/5/10.
 */

public class FragmentMusic extends Fragment{
    private ListView lv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentmusic, null);
        lv= (ListView) v.findViewById(R.id.list_music);

        List<MusicList> list = new ArrayList<MusicList>();
        MusicList m1 = new MusicList(getResources().getDrawable(R.mipmap.ic_launcher),"本地音乐");
        MusicList m2 = new MusicList(getResources().getDrawable(R.mipmap.ic_launcher),"我的收藏");
        MusicList m3 = new MusicList(getResources().getDrawable(R.mipmap.ic_launcher),"我的单台");
        MusicList m4 = new MusicList(getResources().getDrawable(R.mipmap.ic_launcher),"我的音乐");

        list.add(m1);
        list.add(m2);
        list.add(m3);
        list.add(m4);

        ListAdapter listAdapter = new ListAdapter(FragmentMusic.super.getActivity(),list);
        lv.setAdapter(listAdapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Intent intent = new Intent(FragmentMusic.super.getActivity(),LocalMusic.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Toast.makeText(FragmentMusic.super.getActivity(),"我的收藏",Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(FragmentMusic.super.getActivity(),"我的电台",Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Intent intent1 = new Intent(FragmentMusic.super.getActivity(),SongList.class);
                        startActivity(intent1);
                        break;
                }
            }
        });

        return v;

    }
}