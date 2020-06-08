package com.example.weinfo.presenter;

import com.example.weinfo.base.BasePresenter;
import com.example.weinfo.bean.MainBean;
import com.example.weinfo.model.MainModel;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.view.MainView;

import java.util.ArrayList;

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

    @Override
    public void initData() {
        mainModel.initData(new CallBack<ArrayList<MainBean.DataBean.DatasBean>>() {
            @Override
            public void onSuccess(ArrayList<MainBean.DataBean.DatasBean> datasBeans) {
                mView.setData(datasBeans);
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }

}
