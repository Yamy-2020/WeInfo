package com.example.weinfo.model;

import com.example.weinfo.base.BaseModel;
import com.example.weinfo.base.BaseSubscriber;
import com.example.weinfo.bean.RegisterBean;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.util.HttpUtil;
import com.example.weinfo.util.RxUtils;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/9   14:32
 **/
public class RegisterModel extends BaseModel {

    public void register(String name, String psw,final CallBack<RegisterBean> callBack) {
        addDisposable(
                HttpUtil.getInstance()
                        .getLoginService()
                        .register(name, psw, "", "1")
                        .compose(RxUtils.<RegisterBean>rxSchedulerHelper())
                        .subscribeWith(new BaseSubscriber<RegisterBean>() {
                            @Override
                            public void onNext(RegisterBean registerBean) {
                                callBack.onSuccess(registerBean);
                            }
                        })
        );
    }
}
