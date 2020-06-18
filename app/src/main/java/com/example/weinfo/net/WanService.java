package com.example.weinfo.net;

import com.example.weinfo.bean.ItInfoArticle;
import com.example.weinfo.bean.ItInfoTabBean;
import com.example.weinfo.bean.NaviBean;

import io.reactivex.Flowable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/17   14:21
 **/
public interface WanService {
    String sUrl = "https://wanandroid.com/";

    @GET("wxarticle/chapters/json")
    Flowable<ItInfoTabBean> getTab();

    @GET("wxarticle/list/{id}/{page}/json")
    Flowable<ItInfoArticle> getWxarticle(@Path("id") int id,
                                         @Path("page") int page);

    @FormUrlEncoded
    @POST("article/query/{page}/json")
    Flowable<ItInfoArticle> query(@Field("k") String key, @Path("page") int page);

    @GET("navi/json")
    Flowable<NaviBean> getNaviData();
}
