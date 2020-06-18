package com.example.weinfo.model;

import com.example.weinfo.base.BaseModel;
import com.example.weinfo.base.BaseSubscriber;
import com.example.weinfo.bean.SectionsBean;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.util.HttpUtil;
import com.example.weinfo.util.RxUtils;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   10:24
 **/
public class SectionsModel extends BaseModel {
    public void getSections(final CallBack<SectionsBean> callBack) {
        addDisposable(HttpUtil.getInstance()
                .getZhiHuService()
                .getSectionsData()
                .compose(RxUtils.<SectionsBean>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<SectionsBean>() {
                    @Override
                    public void onNext(SectionsBean sectionsBean) {
                        callBack.onSuccess(sectionsBean);
                    }
                }));
    }
}
