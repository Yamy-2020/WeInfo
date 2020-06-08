package com.example.weinfo.net;

import com.example.weinfo.bean.MainBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/6   16:40
 **/
public interface ApiService {
    String url = "https://www.wanandroid.com/";

    @GET("project/list/1/json?cid=0")
    Observable<MainBean> getData();
}
