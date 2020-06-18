package com.example.weinfo.presenter;

import com.example.weinfo.R;
import com.example.weinfo.base.BaseApp;
import com.example.weinfo.base.BasePresenter;
import com.example.weinfo.base.Constants;
import com.example.weinfo.bean.LoginBean;
import com.example.weinfo.model.LoginModel;
import com.example.weinfo.net.CallBack;
import com.example.weinfo.net.LoginService;
import com.example.weinfo.util.SpUtil;
import com.example.weinfo.view.LoginView;
import com.umeng.socialize.bean.SHARE_MEDIA;

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

    public void login(String name, String psw) {
//        loginModel.login(name, psw, new CallBack<LoginBean>() {
//            @Override
//            public void onSuccess(LoginBean loginBean) {
//                //开发使用这种,因为loginBean.getCode()后端给的,有可能是null
//                if (LoginService.LOGIN_SUCCESS_CODE.equals(loginBean.getCode())) {
//                    mView.loginSuccess();
//                    saveUserData(loginBean);
//                } else {
//                    mView.showToast(BaseApp.getRes().getString(R.string.login_fail));
//                }
//            }
//
//            @Override
//            public void onFail(String string) {
//
//            }
//        });
        loginModel.loginEasemob(name, psw, new CallBack<String>() {
            @Override
            public void onSuccess(String string) {
                mView.showToast(string);
                mView.loginSuccess();
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

    public void loginAccess(SHARE_MEDIA platform, final String uid) {
        String typeId = "";
        if (platform == SHARE_MEDIA.QQ) {
            typeId = LoginService.TYPE_QQ;
        } else if (platform == SHARE_MEDIA.WEIXIN) {
            typeId = LoginService.TYPE_WECHAT;
        } else if (platform == SHARE_MEDIA.SINA) {
            typeId = LoginService.TYPE_SINA;
        }
        final String finalTypeId = typeId;
        loginModel.loginAccess(uid, typeId, new CallBack<LoginBean>() {
            @Override
            public void onSuccess(LoginBean loginBean) {
                if (LoginService.LOGIN_SUCCESS_CODE.equals(loginBean.getCode())) {
                    mView.loginSuccess();
                    saveUserData(loginBean);
                } else {
                    //不是登录失败
                    //三方唯一标识并没有在服务器创建过用户
                    //跳转到完善资料页面
                    //完善资料页面和注册长得差不多，所以可以复用页面
                    mView.inputUserInfo(uid, finalTypeId);
                    mView.showToast(BaseApp.getRes().getString(R.string.input_info));
                }
            }

            @Override
            public void onFail(String string) {
                mView.showToast(string);
            }
        });
    }
}
