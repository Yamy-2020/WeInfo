package com.example.weinfo.presenter;

import com.example.weinfo.base.BasePresenter;
import com.example.weinfo.model.DiscoveryModel;
import com.example.weinfo.net.CallBack;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/11   20:25
 **/
public class DiscoveryPresenter extends BasePresenter {

    private DiscoveryModel discoveryModel;

    @Override
    protected void initModel() {
        discoveryModel = new DiscoveryModel();
        models.add(discoveryModel);
    }
    public void addFriend(String friendName) {
        discoveryModel.addFriend(friendName, new CallBack<String>() {
            @Override
            public void onSuccess(String string) {
                mView.showToast(string);
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }

    public void createGroup(String name) {
        discoveryModel.createGroup(name, new CallBack<String>() {
            @Override
            public void onSuccess(String string) {
                mView.showToast(string);
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }

    public void addGroup(String group) {
        discoveryModel.addGroup(group, new CallBack<String>() {
            @Override
            public void onSuccess(String string) {
                mView.showToast(string);
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }
}
