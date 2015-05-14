package com.example.xdd05.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {

    private EditText mEditText;
    private ImageView mImageView;
    public List<String> mList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mEditText = (EditText) findViewById(R.id.et_text);
        mImageView = (ImageView) findViewById(R.id.iv_image);
        mListView = new ListView(this);
        for (int i = 0; i <10 ; i++) {
            mList.add("标题"+i);
        }
        mListView.setBackgroundResource(R.mipmap.listview_background);
        mListView.setAdapter(new Myadapter());

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mEditText.setText(mList.get(position));
                //隐藏窗口
                mPopupWindow.dismiss();
            }
        });
        mImageView.setOnClickListener(this);
    }

    private PopupWindow mPopupWindow;
    private ListView mListView;
    @Override
    public void onClick(View v) {
        if (mPopupWindow == null){
            mPopupWindow = new PopupWindow(this);
            mPopupWindow.setWidth(mEditText.getWidth());
            mPopupWindow.setHeight(200);
            mPopupWindow.setContentView(mListView);

            mPopupWindow.setOutsideTouchable(true);
            mPopupWindow.setFocusable(true); //设置popupwindow可以获得焦点

        }
        mPopupWindow.showAsDropDown(mEditText,0,0);//设置popupwindow的位置
    }

    private class Myadapter extends BaseAdapter {
        @Override
        public int getCount() {
            return mList.size();
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
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view;
            ViewHolder mViewHolder;
            if (convertView == null){
                mViewHolder = new ViewHolder();
                view = getLayoutInflater().inflate(R.layout.item,null);
                mViewHolder.mText = (TextView) view.findViewById(R.id.tv_text);
                mViewHolder.mDelete = (ImageView) view.findViewById(R.id.iv_delete);
                view.setTag(mViewHolder);
            }else {
                view = convertView;
                mViewHolder = (ViewHolder) view.getTag();
            }
            mViewHolder.mText.setText(mList.get(position));
            mViewHolder.mDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.remove(position);
                    notifyDataSetChanged(); //刷新listview
                }
            });
            return view;
        }
    }

    private class ViewHolder{
        TextView mText;
        ImageView mDelete;
    }
}
