package com.example.niehongtao.listviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by niehongtao on 16/4/18.
 */
class MyAdapter extends BaseAdapter {
    private List<String> contents;
    private Context mContext;

    public MyAdapter(List<String> contents, Context context) {
        this.contents = contents;
        mContext = context;
    }

    @Override
    public int getCount() {
        return contents.size();
    }
    @Override
    public Object getItem(int position) {
        return contents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item, parent, false);
            holder = new ViewHolder();
            holder.tvContent = (TextView) convertView.findViewById(R.id.tvContent);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvContent.setText(contents.get(position));
        return convertView;
    }
    class ViewHolder{
        TextView tvContent;
    }
}
