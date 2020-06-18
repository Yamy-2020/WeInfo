package com.example.weinfo.model;

import com.example.weinfo.base.BaseModel;
import com.example.weinfo.base.BaseSubscriber;
import com.example.weinfo.bean.DailyNewsBean;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.util.HttpUtil;
import com.example.weinfo.util.RxUtils;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   10:12
 **/
public class DailyNewsModel extends BaseModel {
    public void getData(final CallBack<DailyNewsBean> callBack) {
        addDisposable(HttpUtil.getInstance()
        .getZhiHuService()
        .getLatest()
        .compose(RxUtils.<DailyNewsBean>rxSchedulerHelper())
        .subscribeWith(new BaseSubscriber<DailyNewsBean>() {
            @Override
            public void onNext(DailyNewsBean dailyNewsBean) {
                callBack.onSuccess(dailyNewsBean);
            }
        }));
    }

    public void getOldNews(String selectTime, final CallBack<DailyNewsBean> callBack) {
        addDisposable(HttpUtil.getInstance()
        .getZhiHuService()
        .getOldNews(selectTime)
        .compose(RxUtils.<DailyNewsBean>rxSchedulerHelper())
        .subscribeWith(new BaseSubscriber<DailyNewsBean>() {
            @Override
            public void onNext(DailyNewsBean dailyNewsBean) {
                callBack.onSuccess(dailyNewsBean);
            }
        }));
    }
}
