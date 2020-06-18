package com.example.weinfo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weinfo.R;
import com.example.weinfo.bean.SectionsBean;

import java.util.ArrayList;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   22:34
 **/
public class SectionsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private ArrayList<SectionsBean.DataBean> list;

    public SectionsAdapter(Context context, ArrayList<SectionsBean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_sections, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv_rv_specail.setText(list.get(position).getName());
        Glide.with(context).load(list.get(position).getThumbnail()).into(((ViewHolder) holder).iv_rv_special);
        viewHolder.tv_rv_specail_desc.setText(list.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView iv_rv_special;
        public TextView tv_rv_specail;
        public TextView tv_rv_specail_desc;

        public ViewHolder(View rootView) {
            super(rootView);
            this.iv_rv_special = (ImageView) rootView.findViewById(R.id.iv_rv_special);
            this.tv_rv_specail = (TextView) rootView.findViewById(R.id.tv_rv_specail);
            this.tv_rv_specail_desc = (TextView) rootView.findViewById(R.id.tv_rv_specail_desc);
        }

    }
}
