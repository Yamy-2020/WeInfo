package com.example.weinfo.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weinfo.R;
import com.example.weinfo.adapter.ItInfoAdapter;
import com.example.weinfo.base.BaseFragment;
import com.example.weinfo.base.Constants;
import com.example.weinfo.bean.ItInfoArticle;
import com.example.weinfo.bean.ItInfoTabBean;
import com.example.weinfo.presenter.ItInfoPresenter;
import com.example.weinfo.view.ItInfoView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ItInfoFragment extends BaseFragment<ItInfoPresenter> implements ItInfoView {
    @BindView(R.id.rv)
    RecyclerView rv;
    Unbinder unbinder;
    private int id;
    private int page = 1;
    private ArrayList<ItInfoArticle.DataBean.DatasBean> list;
    private ItInfoAdapter adapter;

    public static ItInfoFragment getInstance(int id, String name) {
        ItInfoFragment fragment = new ItInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Constants.DATA, id);
        bundle.putString(Constants.USERNAME, name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ItInfoPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_it_info;
    }

    @Override
    protected void initData() {
        mPresenter.getInfoData(id, page);
    }

    @Override
    protected void initView(View view) {
        id = getArguments().getInt(Constants.DATA);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        adapter = new ItInfoAdapter(getActivity(), list);
        rv.setAdapter(adapter);
    }

    @Override
    public void setData(ItInfoTabBean itInfoTabBean) {

    }

    @Override
    public void setData2(ItInfoArticle itInfoArticle) {
        list.addAll(itInfoArticle.getData().getDatas());
        adapter.notifyDataSetChanged();
    }
}