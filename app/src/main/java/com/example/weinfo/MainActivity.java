package com.example.weinfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.weinfo.adapter.MainAdapter;
import com.example.weinfo.base.BaseActivity;
import com.example.weinfo.bean.MainBean;
import com.example.weinfo.presenter.MainPresenter;
import com.example.weinfo.view.MainView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.nv)
    NavigationView nv;
    @BindView(R.id.dl)
    DrawerLayout dl;
    private ArrayList<MainBean.DataBean.DatasBean> list;
    private MainAdapter adapter;

    public static void startAct(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        mPresenter.initData();
    }

    @Override
    protected void initView() {
        toolbar.setTitle("首页");
        setSupportActionBar(toolbar);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new MainAdapter(this, list);
        rv.setAdapter(adapter);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setData(ArrayList<MainBean.DataBean.DatasBean> datasBeans) {
        list.addAll(datasBeans);
        adapter.notifyDataSetChanged();
    }
}
