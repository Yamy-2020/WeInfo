package com.example.weinfo.model;

import com.example.weinfo.base.BaseModel;
import com.example.weinfo.base.BaseSubscriber;
import com.example.weinfo.bean.NewsDailyBean;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.util.HttpUtil;
import com.example.weinfo.util.RxUtils;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   21:38
 **/
public class DailyNewsWebModel extends BaseModel {
    public void getDailyNews(int id, final CallBack<NewsDailyBean> callBack) {
        addDisposable(HttpUtil.getInstance()
                .getZhiHuService()
                .getNewsDetail(id)
                .compose(RxUtils.<NewsDailyBean>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<NewsDailyBean>() {
                    @Override
                    public void onNext(NewsDailyBean newsDailyBean) {
                        callBack.onSuccess(newsDailyBean);
                    }
                }));
    }
}
