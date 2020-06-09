package com.example.weinfo.model;

import com.example.weinfo.base.BaseModel;
import com.example.weinfo.base.BaseSubscriber;
import com.example.weinfo.bean.LoginBean;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.util.HttpUtil;
import com.example.weinfo.util.RxUtils;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/8   16:25
 **/
public class LoginModel extends BaseModel {
    public void login(String name, String psw, final CallBack<LoginBean> callBack) {
        addDisposable(HttpUtil.getInstance()
                .getLoginService()
                .login(name, psw)
                .compose(RxUtils.<LoginBean>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        callBack.onSuccess(loginBean);
                    }
                }));
    }
}
