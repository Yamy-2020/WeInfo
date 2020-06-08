package com.example.weinfo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.weinfo.MainActivity;
import com.example.weinfo.R;
import com.example.weinfo.base.BaseActivity;
import com.example.weinfo.bean.MainBean;
import com.example.weinfo.presenter.LoginPresenter;
import com.example.weinfo.presenter.MainPresenter;
import com.example.weinfo.util.ToastUtil;
import com.example.weinfo.view.LoginView;
import com.example.weinfo.view.MainView;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginView, View.OnClickListener {

    @BindView(R.id.btn_share)
    Button btnShare;

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
        findViewById(R.id.btn_share).setOnClickListener(this);
    }

    @Override
    protected void initPresenter() {

    }

    private void shareBoard() {
        UMImage image = new UMImage(this,R.drawable.i);
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
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_share:
                shareBoard();
                break;
        }
    }

    @Override
    public void showToast(String string) {
        Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
    }
}