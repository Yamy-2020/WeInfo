package com.example.weinfo.model;

import android.util.Log;

import com.example.weinfo.base.BaseModel;
import com.example.weinfo.base.BaseSubscriber;
import com.example.weinfo.bean.LoginBean;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.util.HttpUtil;
import com.example.weinfo.util.RxUtils;
import com.example.weinfo.util.ThreadManager;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

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

    public void loginAccess(String uid, String typeId, final CallBack<LoginBean> callBack) {
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

    public void loginEasemob(String name, String psw, final CallBack<String> callBack) {
        EMClient.getInstance().login(name, psw, new EMCallBack() {//回调
            @Override
            public void onSuccess() {
                //为了保证进入主页面后本地会话和群组都 load 完毕
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");
                callBack.onSuccess("登录成功");
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                Log.d("main", "登录聊天服务器失败！");
                callBack.onFail("登录失败"+message);
            }
        });
    }
}
