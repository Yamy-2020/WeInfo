package com.example.weinfo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.weinfo.R;
import com.example.weinfo.adapter.NavigationAdapter;
import com.example.weinfo.base.BaseApp;
import com.example.weinfo.base.BaseSubscriber;
import com.example.weinfo.bean.NaviBean;
import com.example.weinfo.db.DataBeanDao;
import com.example.weinfo.util.HttpUtil;
import com.example.weinfo.util.RxUtils;

import java.util.ArrayList;

import qdx.stickyheaderdecoration.NormalDecoration;

public class NavigationActivity extends AppCompatActivity {
    private RecyclerView rv;
    private ArrayList<NaviBean.DataBean> list;
    private NavigationAdapter adapter;
    private NormalDecoration decoration;

    public static void startAct(Context context) {
        context.startActivity(new Intent(context, NavigationActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        initView();
        initData();
    }

    private void initData() {
        HttpUtil.getInstance()
                .getWanService()
                .getNaviData()
                .compose(RxUtils.<NaviBean>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<NaviBean>() {
                    @Override
                    public void onNext(NaviBean naviBean) {
                        if (naviBean.getData() != null && naviBean.getData().size() > 0) {
                            list.addAll(naviBean.getData());
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        adapter = new NavigationAdapter(list);
        decoration = new NormalDecoration() {
            @Override
            public String getHeaderName(int pos) {
                return list.get(pos).getName();//返回每个分组头部名称;
            }
        };
//        decoration.setOnDecorationHeadDraw(new NormalDecoration.OnDecorationHeadDraw() {
//            @Override
//            public View getHeaderView(int i) {
//                View view = LayoutInflater.from(NavigationActivity.this).inflate(R.layout.layout_nd, null);
//                TextView headerName= view.findViewById(R.id.tv);
//                headerName.setText(list.get(i).getName());
//                return view;
//            }
//        });
        rv.addItemDecoration(decoration);
        rv.setAdapter(adapter);
    }
}