package com.example.weinfo.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weinfo.R;
import com.example.weinfo.adapter.SectionsAdapter;
import com.example.weinfo.base.BaseFragment;
import com.example.weinfo.bean.SectionsBean;
import com.example.weinfo.presenter.SectionsPresenter;
import com.example.weinfo.view.SectionsView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class SectionsFragment extends BaseFragment<SectionsPresenter> implements SectionsView {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    private ArrayList<SectionsBean.DataBean> list;
    private SectionsAdapter adapter;

    public static SectionsFragment getInstance() {
        SectionsFragment sectionsFragment = new SectionsFragment();
        return sectionsFragment;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new SectionsPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_sections;
    }

    @Override
    protected void initData() {
mPresenter.getSections();
    }

    @Override
    protected void initView(View view) {
        rv.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        list = new ArrayList<>();
        adapter = new SectionsAdapter(getActivity(), list);
        rv.setAdapter(adapter);
    }

    @Override
    public void setData(SectionsBean dataBean) {
        list.addAll(dataBean.getData());
        adapter.notifyDataSetChanged();
    }
}