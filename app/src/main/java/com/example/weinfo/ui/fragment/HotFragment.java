package com.example.weinfo.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weinfo.R;
import com.example.weinfo.adapter.HotAdapter;
import com.example.weinfo.base.BaseFragment;
import com.example.weinfo.bean.HotBean;
import com.example.weinfo.presenter.HotPresenter;
import com.example.weinfo.view.HotView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class HotFragment extends BaseFragment<HotPresenter> implements HotView {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    private ArrayList<HotBean.RecentBean> list;
    private HotAdapter adapter;

    public static HotFragment getInstance() {
        HotFragment hotFragment = new HotFragment();
        return hotFragment;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new HotPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_hot;
    }

    @Override
    protected void initData() {
        mPresenter.getHot();
    }

    @Override
    protected void initView(View view) {
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        list = new ArrayList<>();
        adapter = new HotAdapter(getActivity(), list);
        rv.setAdapter(adapter);
    }

    @Override
    public void setData(HotBean hotBean) {
        list.addAll(hotBean.getRecent());
        adapter.notifyDataSetChanged();
    }
}