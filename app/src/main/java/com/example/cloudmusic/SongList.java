package com.example.cloudmusic;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by 聪 on 2017/6/27.
 */

public class SongList extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.songlist);
        //listView
        listView = (ListView) findViewById(R.id.songlist);
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
                Intent intent1 =new Intent(SongList.this,MusicPlayer.class);
                intent1.putExtra("pos",position);
                startActivity(intent1);
            }
        });
    }
    //四种Adapter  ArrayAdapter    SimpleAdapter   SimpleCursorAdapter   自定义Adapter
    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return   MyApplication.musicList.size();
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
            Music music = MyApplication.musicList.get(position);

            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tv_artist = (TextView) convertView.findViewById(R.id.tv_artist);

            tv_name.setText(music.getName());
            tv_artist.setText(music.getArtist());

            return convertView;
        }
    }
}
