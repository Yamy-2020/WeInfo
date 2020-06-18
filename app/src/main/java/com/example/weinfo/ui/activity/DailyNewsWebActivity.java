package com.example.weinfo.ui.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.weinfo.R;
import com.example.weinfo.base.BaseActivity;
import com.example.weinfo.bean.NewsDailyBean;
import com.example.weinfo.presenter.DailyNewsWebPresenter;
import com.example.weinfo.util.MyImageGetter;
import com.example.weinfo.view.DailyNewsWebView;

import java.util.List;

public class DailyNewsWebActivity extends BaseActivity<DailyNewsWebPresenter> implements DailyNewsWebView {
    private ImageView iv;
    private Toolbar toolbar;
    private CollapsingToolbarLayout ctl;
    private AppBarLayout abl;
    private TextView tv;
    private NestedScrollView nsv;
    private CoordinatorLayout cl;
    private int id;
    private int mWidthPixels;
    private int mHeightPixels;
    private WebView web;
    private FloatingActionButton fab;

    @Override
    protected void initData() {
        mPresenter.getDailyNews(id);
    }

    @Override
    protected void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ctl = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        abl = (AppBarLayout) findViewById(R.id.abl);
        tv = (TextView) findViewById(R.id.tv);
        nsv = (NestedScrollView) findViewById(R.id.nsv);
        cl = (CoordinatorLayout) findViewById(R.id.cl);
        web = (WebView) findViewById(R.id.web);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        id = getIntent().getIntExtra("id", 0);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void initPresenter() {
        mPresenter = new DailyNewsWebPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_daily_news;
    }

    @Override
    public void setData(NewsDailyBean newsDailyBean) {
        ctl.setTitle(newsDailyBean.getTitle());
        ctl.setExpandedTitleColor(getResources().getColor(R.color.black));
        ctl.setCollapsedTitleTextColor(getResources().getColor(R.color.white));
        List<String> images = newsDailyBean.getImages();
        if (images != null) {
            Glide.with(this).load(images.get(0)).into(iv);
        }
        String body = newsDailyBean.getBody();
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        int size = (int) getResources().getDimension(R.dimen.dp_16);
        settings.setDefaultFontSize(size);
        //如果使用loadData(htmlContent,"text/html","utf-8")；会出现文字乱码，
        web.loadDataWithBaseURL(null, body, "text/html", "utf-8", null);
        //扩大比例的缩放
        web.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        web.getSettings().setLoadWithOverviewMode(true);
//        if (!TextUtils.isEmpty(body)) {
//            Spanned spanned = Html.fromHtml(body, new MyImageGetter(this, tv, mWidthPixels), null);
//            tv.setText(spanned);
//        }
    }

//    private void getScreen() {
//        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
//        Display defaultDisplay = manager.getDefaultDisplay();
//        DisplayMetrics outMetrics = new DisplayMetrics();
//        defaultDisplay.getMetrics(outMetrics);
//        mWidthPixels = outMetrics.widthPixels;
//        mHeightPixels = outMetrics.heightPixels;
//    }
}