package com.example.weinfo.presenter;

import com.example.weinfo.base.BasePresenter;
import com.example.weinfo.bean.ItInfoArticle;
import com.example.weinfo.bean.ItInfoTabBean;
import com.example.weinfo.model.ItInfoModel;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.view.ItInfoView;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/17   14:06
 **/
public class ItInfoPresenter extends BasePresenter<ItInfoView> {

    private ItInfoModel itInfoModel;

    @Override
    protected void initModel() {
        itInfoModel = new ItInfoModel();
        models.add(itInfoModel);
    }

    public void getTabData() {
        itInfoModel.getTabData(new CallBack<ItInfoTabBean>() {
            @Override
            public void onSuccess(ItInfoTabBean itInfoTabBean) {
                mView.setData(itInfoTabBean);
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }

    public void getInfoData(int id, int page) {
        itInfoModel.getInfoData(id, page, new CallBack<ItInfoArticle>() {
            @Override
            public void onSuccess(ItInfoArticle itInfoArticle) {
                mView.setData2(itInfoArticle);
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }
}
