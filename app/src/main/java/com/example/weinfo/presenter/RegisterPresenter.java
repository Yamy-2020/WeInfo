package com.example.weinfo.presenter;

import com.example.weinfo.R;
import com.example.weinfo.base.BaseApp;
import com.example.weinfo.base.BasePresenter;
import com.example.weinfo.bean.RegisterBean;
import com.example.weinfo.model.RegisterModel;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.view.RegisterView;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/9   14:31
 **/
public class RegisterPresenter extends BasePresenter<RegisterView> {

    private RegisterModel registerModel;
    private String registerSuccess = BaseApp.getRes().getString(R.string.register_success);

    @Override
    protected void initModel() {
        registerModel = new RegisterModel();
        models.add(registerModel);
    }

    @Override
    public void initData() {

    }

    public void register(String name, String psw) {
        registerModel.register(name, psw,new CallBack<RegisterBean>() {
            @Override
            public void onSuccess(RegisterBean registerBean) {
                if (registerSuccess.equals(registerBean.code)) {
                    mView.registerSuccess();
                } else {
                    mView.showToast(registerBean.code);
                }
            }

            @Override
            public void onFail(String string) {

            }
        });
    }
}
