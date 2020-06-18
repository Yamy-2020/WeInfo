package com.example.weinfo.presenter;

import com.example.weinfo.base.BasePresenter;
import com.example.weinfo.bean.SectionsBean;
import com.example.weinfo.model.SectionsModel;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.view.SectionsView;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   10:20
 **/
public class SectionsPresenter extends BasePresenter<SectionsView> {

    private SectionsModel sectionsModel;

    @Override
    protected void initModel() {
        sectionsModel = new SectionsModel();
        models.add(sectionsModel);
    }

    public void getSections() {
        sectionsModel.getSections(new CallBack<SectionsBean>() {
            @Override
            public void onSuccess(SectionsBean dataBean) {
                mView.setData(dataBean);
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }
}
