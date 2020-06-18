package com.example.weinfo.model;

import com.example.weinfo.base.BaseModel;
import com.example.weinfo.base.BaseSubscriber;
import com.example.weinfo.bean.HotBean;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.util.HttpUtil;
import com.example.weinfo.util.RxUtils;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   10:23
 **/
public class HotModel extends BaseModel {
    public void getHot(final CallBack<HotBean> callBack) {
        addDisposable(HttpUtil.getInstance()
        .getZhiHuService()
        .getHotData()
        .compose(RxUtils.<HotBean>rxSchedulerHelper())
        .subscribeWith(new BaseSubscriber<HotBean>() {
            @Override
            public void onNext(HotBean hotBean) {
                callBack.onSuccess(hotBean);
            }
        }));
    }
}
