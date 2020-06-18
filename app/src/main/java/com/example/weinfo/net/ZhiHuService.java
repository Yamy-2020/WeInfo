package com.example.weinfo.net;

import com.example.weinfo.bean.DailyNewsBean;
import com.example.weinfo.bean.HotBean;
import com.example.weinfo.bean.NewsDailyBean;
import com.example.weinfo.bean.SectionsBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   10:28
 **/
public interface ZhiHuService {
    String sBaseUrl = "http://news-at.zhihu.com/api/4/";
    //最新新闻
    @GET("news/latest")
    Flowable<DailyNewsBean> getLatest();
    //旧新闻
    @GET("news/before/{date}")
    Flowable<DailyNewsBean> getOldNews(@Path("date") String date);
    //详情
    @GET("news/{news_id}")
    Flowable<NewsDailyBean> getNewsDetail(@Path("news_id") int id);
    /**
     * 专栏  special
     * http://news-at.zhihu.com/api/4/sections
     */
    String SPECIAL_URL = "http://news-at.zhihu.com/api/4/";
    @GET("sections")
    Flowable<SectionsBean> getSectionsData();
    /**
     * 热门  hot
     * http://news-at.zhihu.com/api/4/news/hot
     */
    String HOT_URL = "http://news-at.zhihu.com/api/4/";
    @GET("news/hot")
    Flowable<HotBean> getHotData();
}
