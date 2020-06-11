package com.example.weinfo.model;

import com.example.weinfo.R;
import com.example.weinfo.base.BaseApp;
import com.example.weinfo.base.BaseModel;
import com.example.weinfo.base.BaseSubscriber;
import com.example.weinfo.bean.LoginBean;
import com.example.weinfo.bean.RegisterBean;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.util.HttpUtil;
import com.example.weinfo.util.LogUtil;
import com.example.weinfo.util.RxUtils;
import com.example.weinfo.util.ThreadManager;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/9   14:32
 **/
public class RegisterModel extends BaseModel {
    String successCode = BaseApp.getInstance().getString(R.string.register_success);

    public void register(String name, String psw, final CallBack<RegisterBean> callBack) {
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

    public void registerAccess(String name, String psw, final String uid, final String typeId, final CallBack<LoginBean> callBack) {
        addDisposable(
                HttpUtil.getInstance()
                        .getLoginService()
                        .register(name, psw, uid, typeId)
                        .compose(RxUtils.<RegisterBean>rxSchedulerHelper())
                        .subscribeWith(new BaseSubscriber<RegisterBean>() {
                            @Override
                            public void onNext(RegisterBean registerBean) {
                                //注册一个账号
                                if (successCode.equals(registerBean.code)) {
                                    //注册成功，调用三方登录接口登录
                                    loginAccess(uid,typeId,callBack);
                                }else {
                                    //注册失败，一般不会失败(用户名已存在)
                                    callBack.onFail(BaseApp.getRes().getString(R.string.name_repeat));
                                }
                            }
                        })
        );
    }
    public void loginAccess(String uid, String typeId,final CallBack<LoginBean> callBack) {
        addDisposable(HttpUtil.getInstance()
                .getLoginService()
                .loginAccess(uid, typeId)
                .compose(RxUtils.<LoginBean>rxSchedulerHelper())
                .subscribeWith(new BaseSubscriber<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        callBack.onSuccess(loginBean);
                    }
                }));
    }

    public void registerEasemob(final String name, final String psw, final CallBack<String> callBack) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//            }
//        }).start();
        //注册失败会抛出HyphenateException
        //因为开启销毁线程比较消耗资源,所以使用线程池
        //ThreadPoolExecutor();
        ThreadManager.getInstance()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            EMClient.getInstance().createAccount(name, psw);//同步方法
                            callBack.onSuccess(BaseApp.getInstance().getString(R.string.register_success));
                        } catch (HyphenateException e) {
                            e.printStackTrace();
                            LogUtil.logd("00000",e.getMessage());
                            callBack.onFail(BaseApp.getInstance().getString(R.string.register_fail));
                        }
                    }
                });

    }
}
