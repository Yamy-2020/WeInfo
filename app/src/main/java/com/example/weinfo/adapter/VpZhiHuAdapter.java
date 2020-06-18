package com.example.weinfo.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.weinfo.base.BaseFragment;

import java.util.ArrayList;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   10:26
 **/
public class VpZhiHuAdapter extends FragmentStatePagerAdapter {
    private ArrayList<BaseFragment> fragments;
    private ArrayList<String> titles;

    public VpZhiHuAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments, ArrayList<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
    //TabLayout和viewpager配合使用的时候tab是由适配器帮忙创建的
    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
