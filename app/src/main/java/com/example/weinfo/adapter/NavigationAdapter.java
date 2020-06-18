package com.example.weinfo.adapter;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.weinfo.R;
import com.example.weinfo.bean.NaviBean;
import com.example.weinfo.widget.FlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/18   14:49
 **/
public class NavigationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<NaviBean.DataBean> list;
    private LayoutInflater con;
    private int mRed = Color.parseColor("#ff0000");
    private int mGreen = Color.parseColor("#00ff00");
    private int mBlue = Color.parseColor("#0000ff");

    public NavigationAdapter(ArrayList<NaviBean.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        con = LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_navi, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        List<NaviBean.DataBean.ArticlesBean> articles = list.get(position).getArticles();
        for (int i = 0; i < articles.size(); i++) {
            TextView tv = (TextView) con.inflate(R.layout.item_label, null);
            tv.setText(articles.get(i).getTitle());
            if (i % 3 == 0) {
                tv.setTextColor(mRed);
            } else if (i % 3 == 1) {
                tv.setTextColor(mBlue);
            } else {
                tv.setTextColor(mGreen);
            }
            viewHolder.fl.addView(tv);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public FlowLayout fl;

        public ViewHolder(View rootView) {
            super(rootView);
            this.fl = (FlowLayout) rootView.findViewById(R.id.fl);
        }

    }
}
