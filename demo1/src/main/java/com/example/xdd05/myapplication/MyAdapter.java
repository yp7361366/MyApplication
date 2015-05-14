package com.example.xdd05.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by xdd05 on 2015/5/14.
 */
public class MyAdapter extends BaseAdapter {
    private Context ctx;
    private int id;
    public MyAdapter(Context context,int id){
        this.ctx = context;
        this.id = id;
    }
    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(ctx).inflate(R.layout.list_item,null);
            holder.mTextView = (TextView) convertView.findViewById(R.id.list_text);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTextView.setText("内容"+id);
        return convertView;
    }

    private class ViewHolder{
        TextView mTextView;
    }
}
