package com.example.cloudmusic;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 聪 on 2017/6/8.
 */

public class ListAdapter extends BaseAdapter{
    public List<MusicList>list = new ArrayList<MusicList>();
    public LayoutInflater layoutInflater;

    public ListAdapter(FragmentActivity context,List<MusicList>list){
        this.list = list;

        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
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
        View item  = layoutInflater.inflate(R.layout.item,null);
        ImageView im  = (ImageView) item.findViewById(R.id.imageView2);
        TextView tv  = (TextView) item.findViewById(R.id.music_name);

        MusicList m = list.get(position);
        im.setImageDrawable(m.drawable);
        tv.setText(m.name);

        return item;
    }
}
