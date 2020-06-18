package com.example.weinfo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.weinfo.R;
import com.example.weinfo.adapter.RlvSearchAdapter;
import com.example.weinfo.base.BaseSubscriber;
import com.example.weinfo.bean.ItInfoArticle;
import com.example.weinfo.util.HttpUtil;
import com.example.weinfo.util.RxUtils;
import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
    private static final String TAG = "SearchActivity";
    private Toolbar toolbar;
    private MaterialSearchView search_view;
    private RecyclerView rlv;
    private RlvSearchAdapter adapter;
    private int page = 0;

    public static void startAct(Context context) {
        context.startActivity(new Intent(context, SearchActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initListener();
    }

    private void initListener() {
        search_view.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //提交搜索,点击软键盘上的搜索按钮触发
                //ToastUtil.showToastShort("submit:"+query);
                submit(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //搜索文本内容发生改变的监听
                //ToastUtil.showToastShort("change:"+newText);
                return false;
            }
        });
        search_view.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //搜索框显示的回调
                //ToastUtil.showToastShort("show");
            }

            @Override
            public void onSearchViewClosed() {
                //搜索框关闭的回调
                //ToastUtil.showToastShort("close");
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        search_view.setMenuItem(item);
        return true;
    }

    private void submit(String query) {
        HttpUtil.getInstance()
                .getWanService()
                .query(query, page)
                .compose(RxUtils.<ItInfoArticle>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<ItInfoArticle>() {
                    @Override
                    public void onNext(ItInfoArticle itInfoArticle) {
                        if (itInfoArticle != null && itInfoArticle.getData() != null
                                && itInfoArticle.getData().getDatas() != null &&
                                itInfoArticle.getData().getDatas().size() > 0) {
                            adapter.addData(itInfoArticle.getData().getDatas());
                        }
                    }
                });
    }

    private void initView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        search_view = (MaterialSearchView) findViewById(R.id.search_view);
        rlv = (RecyclerView) findViewById(R.id.rlv);
        setSupportActionBar(toolbar);
        rlv.setLayoutManager(new LinearLayoutManager(this));
        ArrayList<ItInfoArticle.DataBean.DatasBean> list = new ArrayList<>();
        adapter = new RlvSearchAdapter(list);
        rlv.setAdapter(adapter);
    }

    //点击返回键的回调方法
    @Override
    public void onBackPressed() {
        if (search_view.isSearchOpen()) {
            search_view.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}