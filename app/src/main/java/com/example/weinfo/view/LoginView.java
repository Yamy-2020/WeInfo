package com.example.weinfo.view;

import com.example.weinfo.base.BaseView;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/8   16:26
 **/
public interface LoginView extends BaseView {
    //登录成功
    void loginSuccess();

    //完善资料
    void inputUserInfo(String uid, String finalTypeId);
}
