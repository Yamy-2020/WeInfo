package com.example.weinfo.ui.activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.example.weinfo.R;
import com.example.weinfo.adapter.ItInfoManagerAdapter;
import com.example.weinfo.adapter.VpItInfoAdapter;
import com.example.weinfo.base.BaseActivity;
import com.example.weinfo.base.BaseApp;
import com.example.weinfo.base.BaseFragment;
import com.example.weinfo.base.Constants;
import com.example.weinfo.bean.DataBean;
import com.example.weinfo.bean.ItInfoArticle;
import com.example.weinfo.bean.ItInfoTabBean;
import com.example.weinfo.db.DataBeanDao;
import com.example.weinfo.presenter.ItInfoPresenter;
import com.example.weinfo.ui.fragment.ItInfoFragment;
import com.example.weinfo.util.LogUtil;
import com.example.weinfo.view.ItInfoView;
import com.umeng.socialize.media.Base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ItInfoActivity extends BaseActivity<ItInfoPresenter> implements ItInfoView, View.OnClickListener {
    private Toolbar toolbar;
    private TabLayout tab;
    private ViewPager vp;
    private ImageView iv;
    private ArrayList<DataBean> list;
    private ArrayList<BaseFragment> fragments;
    private VpItInfoAdapter adapter;

    public static void startAct(Context context) {
        context.startActivity(new Intent(context, ItInfoActivity.class));
    }

    @Override
    protected void initData() {
        mPresenter.getTabData();
    }

    @Override
    protected void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        tab = (TabLayout) findViewById(R.id.tab);
        vp = (ViewPager) findViewById(R.id.vp);
        iv = (ImageView) findViewById(R.id.iv);
        toolbar.setTitle("IT资讯");
        setSupportActionBar(toolbar);
        iv.setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ItInfoPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_i_t_info;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(0, 0, 0, "搜索").setIcon(R.mipmap.qwe).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        menu.add(0, 1, 0, "导航").setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                SearchActivity.startAct(this);
                break;
            case 1:
                NavigationActivity.startAct(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setData(ItInfoTabBean itInfoTabBean) {
        list = itInfoTabBean.getData();
        fragments = new ArrayList<>();
        ArrayList<DataBean> all = (ArrayList<DataBean>) BaseApp.getInstance().getDaoSession().getDataBeanDao()
                .queryBuilder().list();
        if (all != null && all.size() > 0) {
            //保存过，要按照index中的顺序进行排序
            Collections.sort(all, new Comparator<DataBean>() {
                @Override
                public int compare(DataBean o1, DataBean o2) {
                    return o1.getIndex() - o2.getIndex();
                }
            });
            for (int i = 0; i < list.size(); i++) {
                //网络数据
                DataBean dataBean = list.get(i);
                boolean isHave = false;
                for (int j = 0; j < all.size(); j++) {
                    DataBean bean = all.get(j);
                    if (bean.getName().equals(dataBean.getName())) {
                        isHave = true;
                        break;
                    }
                }
                if (isHave) {
                    //数据库和网络都有
                } else {
                    //网络有，数据库没有
                    dataBean.setIndex(all.size());
                    all.add(dataBean);
                }
            }
            for (int i = 0; i < all.size(); i++) {
                DataBean bean = all.get(i);
                boolean isHave = false;
                for (int j = 0; j < list.size(); j++) {
                    DataBean dataBean = list.get(j);
                    if (dataBean.getName().equals(bean.getName())) {
                        isHave = true;
                        break;
                    }
                }
                if (isHave) {

                } else {
                    all.remove(bean);
                }
            }
            for (int i = 0; i <all.size() ; i++) {
                all.get(i).setIndex(i);
            }
            list = all;
        } else {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setIndex(i);
            }
        }
        saveData();
        initFragment();
        initVp();
    }

    private void saveData() {
        DataBeanDao dao = BaseApp.getInstance().getDaoSession().getDataBeanDao();
        dao.insertOrReplaceInTx(list);
    }

    private void initFragment() {
        fragments.clear();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIsInterested()) {
                fragments.add(ItInfoFragment.getInstance(list.get(i).getId(), list.get(i).getName()));
            }
        }
    }

    @Override
    public void setData2(ItInfoArticle itInfoArticle) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv:
                ItInfoManagerActivity.startAct(this, list);
//                Intent intent = new Intent(ItInfoActivity.this, ItInfoManagerActivity.class);
//                for (int i = 0; i <data.size() ; i++) {
//                    String name = data.get(i).getName();
//                    intent.putExtra("tab",name);
//                }
//                startActivityForResult(intent,100);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            list = (ArrayList<DataBean>) data.getSerializableExtra(Constants.DATA);
            initFragment();
            initVp();
            adapter.notifyDataSetChanged();
        }
    }

    private void initVp() {
        adapter = new VpItInfoAdapter(getSupportFragmentManager(), list, fragments);
        vp.setAdapter(adapter);
        tab.setupWithViewPager(vp);
    }
}