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

public class MainActivity extends Activity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private EditText mTitle;
    private ImageView mSelect;
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitle = (EditText) findViewById(R.id.title_text);
        mSelect = (ImageView) findViewById(R.id.iv_select);
        mListView = (ListView) findViewById(R.id.select_listview);
        listview = new ListView(this);
        listview.setAdapter(new Myadapter());
        mSelect.setOnClickListener(this);
        listview.setOnItemClickListener(this);
    }
    private ListView listview;
    private PopupWindow mPopupWindow;
    @Override
    public void onClick(View v) {
        if (mPopupWindow == null){
            mPopupWindow = new PopupWindow(this);

            mPopupWindow.setWidth(mTitle.getWidth());

            mPopupWindow.setHeight(200);

            mPopupWindow.setContentView(listview);
            mPopupWindow.setOutsideTouchable(true);//设置外围可点击且点击收回窗口
            mPopupWindow.setFocusable(true); //设置可以获得焦点
        }
        mPopupWindow.showAsDropDown(mTitle,0,0);//设置窗口位置
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mTitle.setText("标题"+position);
        mListView.setAdapter(new MyAdapter(getApplicationContext(),position));
        mPopupWindow.dismiss();
    }

    private class Myadapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 5;
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
                convertView = getLayoutInflater().inflate(R.layout.item,null);
                holder.item_textview = (TextView) convertView.findViewById(R.id.item);
                convertView.setTag(holder);
            }else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.item_textview.setText("标题"+position);
            return convertView;
        }
    }

    private class ViewHolder{
        TextView item_textview;
    }
}
