package com.example.weinfo.presenter;

import android.service.carrier.CarrierMessagingService;
import android.widget.Toast;

import com.example.weinfo.R;
import com.example.weinfo.base.BaseApp;
import com.example.weinfo.base.BasePresenter;
import com.example.weinfo.base.Constants;
import com.example.weinfo.bean.LoginBean;
import com.example.weinfo.bean.RegisterBean;
import com.example.weinfo.model.RegisterModel;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.net.LoginService;
import com.example.weinfo.ui.activity.RegisterActivity;
import com.example.weinfo.util.SpUtil;
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

    public void register(String name, String psw) {
//        registerModel.register(name, psw, new CallBack<RegisterBean>() {
//            @Override
//            public void onSuccess(RegisterBean registerBean) {
//                if (registerSuccess.equals(registerBean.code)) {
//                    mView.registerSuccess();
//                } else {
//                    mView.showToast(registerBean.code);
//                }
//            }
//
//            @Override
//            public void onFail(String string) {
//
//            }
//        });
        //环信注册
        registerModel.registerEasemob(name, psw, new CallBack<String>() {
            @Override
            public void onSuccess(String string) {
                mView.showToast(string);
                mView.registerSuccess();
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }

    public void registerAccess(String name, String psw, String uid, String typeId) {
        registerModel.registerAccess(name, psw, uid, typeId, new CallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                if (LoginService.LOGIN_SUCCESS_CODE.equals(loginBean.getCode())) {
                    mView.loginSuccess();
                    saveUserData(loginBean);
                } else {
                    mView.showToast(BaseApp.getRes().getString(R.string.login_fail));
                }
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }

    private void saveUserData(LoginBean loginBean) {
        LoginBean.UserBean user = loginBean.getUser();
        String token = loginBean.getToken();
        SpUtil.setParam(Constants.TOKEN, token);
        SpUtil.setParam(Constants.USERNAME, user.getName());
        SpUtil.setParam(Constants.USERID, user.getUserid());
        SpUtil.setParam(Constants.USERID, user.getHeaderpic());

    }
}
