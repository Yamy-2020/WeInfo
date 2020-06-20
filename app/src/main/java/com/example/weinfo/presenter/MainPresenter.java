package com.example.weinfo.presenter;

import com.example.weinfo.base.BasePresenter;
import com.example.weinfo.bean.MainBean;
import com.example.weinfo.model.MainModel;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.view.MainView;
import com.hyphenate.easeui.domain.EaseUser;

import java.util.ArrayList;
import java.util.Map;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/6   16:43
 **/
public class MainPresenter extends BasePresenter<MainView> {

    private MainModel mainModel;

    @Override
    protected void initModel() {
        mainModel = new MainModel();
        models.add(mainModel);
    }

    public void initData() {
        mainModel.initData(new CallBack<Map<String, EaseUser>>() {
            @Override
            public void onSuccess(Map<String, EaseUser> stringEaseUserMap) {
                if (mView != null) {
                    mView.setData(stringEaseUserMap);
                }
                mView.setData(stringEaseUserMap);
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }
}
