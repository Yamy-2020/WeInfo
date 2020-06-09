package com.example.weinfo.presenter;

import com.example.weinfo.R;
import com.example.weinfo.base.BaseApp;
import com.example.weinfo.base.BasePresenter;
import com.example.weinfo.bean.LoginBean;
import com.example.weinfo.model.LoginModel;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.net.LoginService;
import com.example.weinfo.view.LoginView;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/8   16:25
 **/
public class LoginPresenter extends BasePresenter<LoginView> {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
        models.add(loginModel);
    }

    @Override
    public void initData() {

    }

    public void login(String name, String psw) {
        loginModel.login(name, psw, new CallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                //开发使用这种,因为loginBean.getCode()后端给的,有可能是null
                if (LoginService.LOGIN_SUCCESS_CODE.equals(loginBean.getCode())){
                    mView.loginSuccess();
                }else {
                    mView.showToast(BaseApp.getRes().getString(R.string.login_fail));
                }
            }

            @Override
            public void onFail(String string) {

            }
        });
    }
}
