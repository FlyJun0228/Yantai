package com.example.yantai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yantai.Table.Note;

import java.util.List;

public class NoteAdapter extends BaseAdapter {
    private List<Note> mList;
    private LayoutInflater layoutInflater;

    public NoteAdapter(Context context, List<Note> list) {
        mList = list;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = layoutInflater.inflate(R.layout.shequ_item, null);
            viewHolder.textView = (TextView) view.findViewById(R.id.new_title);
            viewHolder.textView1 = (TextView) view.findViewById(R.id.new_time);
            viewHolder.textView2 = (TextView) view.findViewById(R.id.new_content);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.textView.setText("标题:" + mList.get(i).getTitle());
        viewHolder.textView1.setText("时间:" + mList.get(i).getTime());
        viewHolder.textView2.setText(mList.get(i).getContent());
        return view;
    }

    public final class ViewHolder {
        public TextView textView, textView1, textView2, textView3, textView4;
        public ImageView imageView;
    }
}