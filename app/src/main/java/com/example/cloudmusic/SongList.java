package com.example.cloudmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 聪 on 2017/6/27.
 */

public class SongList extends AppCompatActivity {
    private ListView listView;
    private List<Music> songList;
    private TextView tv_top;
    private ImageButton img_back;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songlist);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        tv_top = (TextView) findViewById(R.id.tv_top);
        img_back = (ImageButton) findViewById(R.id.img_back);

        tv_top.setText("我的收藏");
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SongList.this,MainActivity.class);
                startActivity(intent);
            }
        });


        //listView
        listView = (ListView) findViewById(R.id.songlist);

        songList = new ArrayList<>();
        Music music = new Music();
        songList.add(music);

        //适配器
        MyAdapter adapter = new MyAdapter();
        //适配器注入
        listView.setAdapter(adapter);

        listView.setAdapter(adapter);
        //listview 的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //启动活动
                Intent intent =new Intent(SongList.this,MusicPlayer.class);
                intent.putExtra("pos",position);
                startActivity(intent);
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return  songList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(SongList.this).inflate(R.layout.item_music,null);
            }
            Music music =  songList.get(position);

            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tv_artist = (TextView) convertView.findViewById(R.id.tv_artist);

            tv_name.setText(music.getName());
            tv_artist.setText(music.getArtist());

            return convertView;
        }
    }
}
