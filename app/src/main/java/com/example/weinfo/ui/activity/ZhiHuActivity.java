package com.example.weinfo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.weinfo.R;
import com.example.weinfo.adapter.VpZhiHuAdapter;
import com.example.weinfo.base.BaseApp;
import com.example.weinfo.base.BaseFragment;
import com.example.weinfo.ui.fragment.DailyNewsFragment;
import com.example.weinfo.ui.fragment.HotFragment;
import com.example.weinfo.ui.fragment.SectionsFragment;

import java.util.ArrayList;

public class ZhiHuActivity extends AppCompatActivity {
    private TabLayout tab;
    private ViewPager vp;
    private ArrayList<BaseFragment> fragments;
    private ArrayList<String> title;

    public static void startAct(Context context) {
        context.startActivity(new Intent(context, ZhiHuActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhi_hu);
        initView();
    }

    private void initView() {
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        initTitles();
        initFragment();
        VpZhiHuAdapter adapter = new VpZhiHuAdapter(getSupportFragmentManager(), fragments, title);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(DailyNewsFragment.getInstance());
        fragments.add(SectionsFragment.getInstance());
        fragments.add(HotFragment.getInstance());
    }

    private void initTitles() {
        title = new ArrayList<>();
        title.add(BaseApp.getRes().getString(R.string.daliy_news));
        title.add(BaseApp.getRes().getString(R.string.sections));
        title.add(BaseApp.getRes().getString(R.string.hot));
    }
}