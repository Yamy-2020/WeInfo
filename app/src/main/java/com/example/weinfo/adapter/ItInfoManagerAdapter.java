package com.example.weinfo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.weinfo.R;
import com.example.weinfo.bean.DataBean;
import com.example.weinfo.bean.ItInfoTabBean;
import com.example.weinfo.net.TouchCallBack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/18   9:18
 **/
public class ItInfoManagerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements TouchCallBack {
    private Context context;
    private ArrayList<DataBean> list;

    public ItInfoManagerAdapter(Context context, ArrayList<DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_manager, null);
        return new ViewHolder(view);
    }

    /**
     * 子条目会复用，为了避免混乱，用数据控制界面
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.tv.setText(list.get(position).getName());
        viewHolder.sc.setChecked(list.get(position).getIsInterested());
        ((ViewHolder) holder).sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isPressed()){//是否按压
                    //说明是用户的行为,不是代码操作
                    list.get(position).setIsInterested(isChecked);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        //交换位置
        Collections.swap(list,fromPosition,toPosition);
        //局部刷新(移动)
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDelete(int position) {
        //删除数据
        list.remove(position);
        //局部刷新(移除)
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public SwitchCompat sc;

        public ViewHolder(View rootView) {
            super(rootView);
            this.tv = (TextView) rootView.findViewById(R.id.tv);
            this.sc = (SwitchCompat) rootView.findViewById(R.id.sc);
        }

    }
}
