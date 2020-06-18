package com.example.weinfo.presenter;

import com.example.weinfo.base.BasePresenter;
import com.example.weinfo.bean.HotBean;
import com.example.weinfo.model.HotModel;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.view.HotView;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   10:23
 **/
public class HotPresenter extends BasePresenter<HotView> {

    private HotModel hotModel;

    @Override
    protected void initModel() {
        hotModel = new HotModel();
        models.add(hotModel);
    }

    public void getHot() {
        hotModel.getHot(new CallBack<HotBean>() {
            @Override
            public void onSuccess(HotBean hotBean) {
                mView.setData(hotBean);
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }
}
