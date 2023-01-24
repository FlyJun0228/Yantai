package com.example.yantai;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.yantai.Bean.YanBean;

import java.util.List;

public class REAdapter extends RecyclerView.Adapter<REAdapter.VH>{
    //② 创建ViewHolder
    private List<YanBean> mDatas;
    private Context context;
    private OnRecyclerViewItemClickListener myClickItemListener;
    public static class VH extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView title;
        public final TextView content;
        public final ImageView imageView;
        private OnRecyclerViewItemClickListener mListener;
        public  VH(View v,OnRecyclerViewItemClickListener listener){
            super(v);
            this.mListener=listener;
            title = (TextView) v.findViewById(R.id.new_title);
            content = (TextView) v.findViewById(R.id.new_content);
            imageView = (ImageView) v.findViewById(R.id.new_bg);
        }

        @Override
        public void onClick(View view) {
            mListener.onItemClick(view,getAdapterPosition());
        }
    }

    public interface OnRecyclerViewItemClickListener {
         void onItemClick(View view, int postion);
    }


    public REAdapter(List<YanBean> data, Context context) {data = data;
        this.context = context;
    }
    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.myClickItemListener = listener;
    }
    //③ 在Adapter中实现3个方法
    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.title.setText(mDatas.get(position).getTitle());
        holder.content.setText(mDatas.get(position).getContent());
        holder.imageView.setImageResource(mDatas.get(position).getImg());
        if (myClickItemListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myClickItemListener.onItemClick(holder.itemView, holder.getLayoutPosition());
                }
            });
        }

        }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        //LayoutInflater.from指定写法
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        VH vh = new VH(v,myClickItemListener);
        return vh;
    }



}
