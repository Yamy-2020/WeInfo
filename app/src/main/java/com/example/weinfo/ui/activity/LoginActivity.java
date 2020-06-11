package com.example.weinfo.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weinfo.MainActivity;
import com.example.weinfo.R;
import com.example.weinfo.base.BaseActivity;
import com.example.weinfo.base.BaseApp;
import com.example.weinfo.base.Constants;
import com.example.weinfo.bean.FinishEvent;
import com.example.weinfo.presenter.LoginPresenter;
import com.example.weinfo.util.LogUtil;
import com.example.weinfo.util.SpUtil;
import com.example.weinfo.view.LoginView;
import com.hyphenate.chat.EMClient;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView {
    @BindView(R.id.btn_share)
    Button btnShare;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_registe)
    TextView tvRegiste;
    @BindView(R.id.btn_share1)
    Button btnShare1;
    @BindView(R.id.btn_share2)
    Button btnShare2;
    @BindView(R.id.btn_share3)
    Button btnShare3;
    @BindView(R.id.base_line)
    View baseLine;
    @BindView(R.id.btn_qq)
    ImageView btnQq;
    @BindView(R.id.btn_wx)
    ImageView btnWx;
    @BindView(R.id.btn_wb)
    ImageView btnWb;
    @BindView(R.id.third_login_desc)
    TextView thirdLoginDesc;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    //注意onActivityResult不可在fragment中实现，
    // 如果在fragment中调用登录或分享，
    // 需要在fragment依赖的Activity中实现
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        //注册EventBus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
        toolbar.setTitle("登录");
        setSupportActionBar(toolbar);
        //怎么判断登录过没有，通过本地用户信息判断
        //在登录的时候把用户信息保存下来
        //取用户信息
//        String token = (String) SpUtil.getParam(Constants.TOKEN, "");
//        if (!TextUtils.isEmpty(token)) {
//            MainActivity.startAct(this);
//            finish();
//        }
        if (EMClient.getInstance().isLoggedInBefore()){
            loginSuccess();
        }
        initPers();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe()
    public void onReceiveFinishEvent(FinishEvent finishEvent) {
        finish();
    }

    private void initPers() {
        String[] pers = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA,
                Manifest.permission.RECORD_AUDIO,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_PHONE_STATE
        };
        ActivityCompat.requestPermissions(this, pers, 100);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new LoginPresenter();
    }

    @OnClick({R.id.btn_login, R.id.tv_registe, R.id.btn_qq, R.id.btn_wx, R.id.btn_wb})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share:
                shareBoard();
                break;
            case R.id.btn_share1:
                share(SHARE_MEDIA.QQ);
                break;
            case R.id.btn_share2:
                share(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.btn_share3:
                share(SHARE_MEDIA.SINA);
                break;
            case R.id.btn_qq:
                login(SHARE_MEDIA.QQ);
                String qqUnique="qq2577385715";
                mPresenter.loginAccess(SHARE_MEDIA.QQ, qqUnique);
                break;
            case R.id.btn_wx:
                login(SHARE_MEDIA.WEIXIN);
                String wxUnique="wx2577385715";
                mPresenter.loginAccess(SHARE_MEDIA.QQ, wxUnique);
                break;
            case R.id.btn_wb:
                login(SHARE_MEDIA.SINA);
                String wbUnique="wb2577385715";
                mPresenter.loginAccess(SHARE_MEDIA.QQ, wbUnique);
                break;
            case R.id.tv_registe:
                //
                RegisterActivity.startAct(this, RegisterActivity.TYPE_REGISTER);
                break;
            case R.id.btn_login:
                login();
                break;
        }
    }

    private void login() {
        String name = etName.getText().toString().trim();
        String psw = etPsw.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(psw)) {
            showToast(BaseApp.getRes().getString(R.string.register_name_or_psw_not_null));
            return;
        }
        mPresenter.login(name, psw);
    }

    private void shareBoard() {
        UMImage image = new UMImage(this, R.drawable.i);
        new ShareAction(LoginActivity.this)
                .withText("hello")//分享文本
                .withMedia(image)//分享媒体消息
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)//分享的平台
                .setCallback(shareListener).open();
    }

    private UMShareListener shareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            showToast("成功了");
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            showToast("失败" + t.getMessage());
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            showToast("取消了");
        }
    };

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void loginSuccess() {
        MainActivity.startAct(this);
        //关闭当前页面
        finish();
    }

    //跳转到完善资料页面
    @Override
    public void inputUserInfo(String uid, String finalTypeId) {
        RegisterActivity.startAct(this, RegisterActivity.TYPE_INFO, uid, finalTypeId);
    }


    private void login(SHARE_MEDIA type) {
        UMShareAPI.get(this).getPlatformInfo(this, type, authListener);
    }

    UMAuthListener authListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            showToast("成功了");
            print(data);
            //data   里面有所有用户在三方平台的信息
            //拿唯一标识判断是否创建过用户
            mPresenter.loginAccess(platform, data.get("uid"));
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            showToast("失败：" + t.getMessage());
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            showToast("取消了");
        }
    };

    private void print(Map<String, String> data) {
        //map遍历    2种：
        //data.keySet();所有键的集合
        //data.entrySet();键值对的集合
        for (Map.Entry<String, String> entry : data.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            LogUtil.print(key + ":" + value);
        }
    }

    private void share(SHARE_MEDIA type) {
        UMImage image = new UMImage(this, R.drawable.i);
        new ShareAction(LoginActivity.this)
                .setPlatform(type)//传入平台
                .withText("hello")//分享内容
                .withMedia(image)
                .setCallback(shareListener)//回调监听器
                .share();
    }
}