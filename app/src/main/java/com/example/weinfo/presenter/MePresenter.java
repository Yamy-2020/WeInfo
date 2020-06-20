package com.example.weinfo.presenter;

import android.view.View;

import com.example.weinfo.base.BaseFragment;
import com.example.weinfo.base.BasePresenter;
import com.example.weinfo.util.ThreadManager;
import com.example.weinfo.view.MeView;
import com.hyphenate.chat.EMClient;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/13   20:34
 **/
public class MePresenter extends BasePresenter<MeView> {

    @Override
    protected void initModel() {

    }

    public void logout() {
        ThreadManager.getInstance()
                .execute(new Runnable() {
                    @Override
                    public void run() {
                        //退出登录
                        EMClient.getInstance().logout(true);
                        //清除用户资料
                        deleteUserData();
                        mView.logoutSuccess();
                    }
                });
    }

    private void deleteUserData() {

    }
}
