package com.example.cloudmusic;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

public class LocalMusic extends AppCompatActivity {
    private ListView listView;
    private ImageButton img_back;
    private TextView tv_top;
    private Button button;

    //是否显示按钮
    @Override
    protected void onResume() {
        super.onResume();

        if(PlayService.mediaPlayer != null && PlayService.mediaPlayer.isPlaying()){
            button.setVisibility(View.VISIBLE);
        }else {
            button.setVisibility(View.GONE);
        }

        button.setText("正在播放："+MyApplication.MusicName+">>>");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.localmusic);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        button = (Button) findViewById(R.id.play);

        button.setText("正在播放："+MyApplication.MusicName);

        button.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalMusic.this,MusicPlayer.class);
                startActivity(intent);
            }
        });

        tv_top = (TextView) findViewById(R.id.tv_top);
        tv_top.setText("本地音乐");
        img_back= (ImageButton) findViewById(R.id.img_back);
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LocalMusic.this,MainActivity.class);
                startActivity(intent);
            }
        });
        //listView
        listView = (ListView) findViewById(R.id.lv_data);
        //适配器
        MyAdapter adapter = new MyAdapter();
        //适配器注入
        listView.setAdapter(adapter);
        //listview 的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //启动活动
                Intent intent = new Intent(LocalMusic.this,PlayService.class);
                intent.putExtra("pos",position);
                startService(intent);
                Intent intent1 =new Intent(LocalMusic.this,MusicPlayer.class);
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
                convertView = LayoutInflater.from(LocalMusic.this).inflate(R.layout.item_music,null);
            }
            Music music = MyApplication.musicList.get(position);

            TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
            TextView tv_artist = (TextView) convertView.findViewById(R.id.tv_artist);

            tv_name.setText(music.getName());
            tv_artist.setText(music.getArtist());

            return convertView;
        }
    }
    protected void onDestroy() {
        super.onDestroy();
    }
}