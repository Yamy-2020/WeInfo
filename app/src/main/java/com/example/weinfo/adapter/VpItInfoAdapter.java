package com.example.weinfo.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.weinfo.base.BaseFragment;
import com.example.weinfo.bean.DataBean;
import com.example.weinfo.bean.ItInfoTabBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/17   15:58
 **/

public class VpItInfoAdapter extends FragmentStatePagerAdapter {
    private List<DataBean> list;
    private ArrayList<BaseFragment> fragments;
    private final ArrayList<String> titles;

    public VpItInfoAdapter(FragmentManager fm, List<DataBean> list, ArrayList<BaseFragment> fragments) {
        super(fm);
        this.list = list;
        this.fragments = fragments;
        titles = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            DataBean bean = list.get(i);
            if (bean.getIsInterested()) {
                titles.add(bean.getName());
            }
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
