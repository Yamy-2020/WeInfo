package com.example.weinfo.presenter;

import com.example.weinfo.base.BasePresenter;
import com.example.weinfo.bean.DailyNewsBean;
import com.example.weinfo.bean.NewsDailyBean;
import com.example.weinfo.model.DailyNewsWebModel;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.view.DailyNewsWebView;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   21:38
 **/
public class DailyNewsWebPresenter extends BasePresenter<DailyNewsWebView> {

    private DailyNewsWebModel dailyNewsWebModel;

    @Override
    protected void initModel() {
        dailyNewsWebModel = new DailyNewsWebModel();
        models.add(dailyNewsWebModel);
    }

    public void getDailyNews(int id) {
        dailyNewsWebModel.getDailyNews(id, new CallBack<NewsDailyBean>() {
            @Override
            public void onSuccess(NewsDailyBean newsDailyBean) {
                mView.setData(newsDailyBean);
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }
}
