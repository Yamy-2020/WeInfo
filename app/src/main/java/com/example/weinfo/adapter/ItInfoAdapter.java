package com.example.weinfo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weinfo.R;
import com.example.weinfo.bean.ItInfoArticle;

import java.util.ArrayList;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/17   17:01
 **/
public class ItInfoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<ItInfoArticle.DataBean.DatasBean> list;

    public ItInfoAdapter(Context context, ArrayList<ItInfoArticle.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_itinfo_rv, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv.setText(list.get(position).getAuthor());
        viewHolder.tv_date.setText(list.get(position).getNiceShareDate());
        viewHolder.tv_title.setText(list.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public TextView tv_title;
        public TextView tv_date;

        public ViewHolder(View rootView) {
            super(rootView);
            this.tv = (TextView) rootView.findViewById(R.id.tv);
            this.tv_title = (TextView) rootView.findViewById(R.id.tv_title);
            this.tv_date = (TextView) rootView.findViewById(R.id.tv_date);
        }

    }
}
