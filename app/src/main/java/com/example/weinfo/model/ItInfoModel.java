package com.example.weinfo.model;

import com.example.weinfo.base.BaseModel;
import com.example.weinfo.base.BaseSubscriber;
import com.example.weinfo.bean.ItInfoArticle;
import com.example.weinfo.bean.ItInfoTabBean;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.util.HttpUtil;
import com.example.weinfo.util.RxUtils;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/17   14:07
 **/
public class ItInfoModel extends BaseModel {
    public void getTabData(final CallBack<ItInfoTabBean> callBack) {
        addDisposable(HttpUtil.getInstance()
                .getWanService()
                .getTab()
                .compose(RxUtils.<ItInfoTabBean>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<ItInfoTabBean>() {
                    @Override
                    public void onNext(ItInfoTabBean itInfoTabBean) {
                        callBack.onSuccess(itInfoTabBean);
                    }
                }));
    }

    public void getInfoData(int id, int page, final CallBack<ItInfoArticle> callBack) {
        addDisposable(HttpUtil.getInstance()
                .getWanService()
                .getWxarticle(id, page)
                .compose(RxUtils.<ItInfoArticle>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<ItInfoArticle>() {
                    @Override
                    public void onNext(ItInfoArticle itInfoArticle) {
                        callBack.onSuccess(itInfoArticle);
                    }
                }));
    }
}
