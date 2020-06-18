package com.example.weinfo.presenter;

import com.example.weinfo.base.BasePresenter;
import com.example.weinfo.bean.DailyNewsBean;
import com.example.weinfo.model.DailyNewsModel;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.view.DailyNewsView;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   10:11
 **/
public class DailyNewsPresenter extends BasePresenter<DailyNewsView> {

    private DailyNewsModel dailyNewsModel;

    @Override
    protected void initModel() {
        dailyNewsModel = new DailyNewsModel();
        models.add(dailyNewsModel);
    }

    public void getData() {
        dailyNewsModel.getData(new CallBack<DailyNewsBean>() {
            @Override
            public void onSuccess(DailyNewsBean dailyNewsBean) {
                mView.setData(dailyNewsBean);
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }

    public void getOldNews(String selectTime) {
        dailyNewsModel.getOldNews(selectTime, new CallBack<DailyNewsBean>() {
            @Override
            public void onSuccess(DailyNewsBean dailyNewsBean) {
                mView.setData(dailyNewsBean);
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }
}
