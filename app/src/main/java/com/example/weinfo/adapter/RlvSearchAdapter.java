package com.example.weinfo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weinfo.R;
import com.example.weinfo.bean.ItInfoArticle;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/17   18:51
 **/
public class RlvSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<ItInfoArticle.DataBean.DatasBean> list;

    public RlvSearchAdapter(ArrayList<ItInfoArticle.DataBean.DatasBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv_name.setText(list.get(position).getAuthor());
        viewHolder.tv_src.setText(list.get(position).getSuperChapterName() + "." + list.get(position).getAuthor());
        viewHolder.tv_time.setText(list.get(position).getNiceShareDate());
        viewHolder.tv_title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addData(List<ItInfoArticle.DataBean.DatasBean> datas) {
        list.addAll(datas);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_name;
        public TextView tv_time;
        public TextView tv_title;
        public TextView tv_src;

        public ViewHolder(View rootView) {
            super(rootView);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_time = (TextView) rootView.findViewById(R.id.tv_time);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_src = (TextView) rootView.findViewById(R.id.tv_src);
        }

    }
}
