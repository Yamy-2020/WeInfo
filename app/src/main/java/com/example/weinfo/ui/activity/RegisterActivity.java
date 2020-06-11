package com.example.weinfo.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.weinfo.MainActivity;
import com.example.weinfo.R;
import com.example.weinfo.base.BaseActivity;
import com.example.weinfo.base.BaseApp;
import com.example.weinfo.base.Constants;
import com.example.weinfo.bean.FinishEvent;
import com.example.weinfo.presenter.RegisterPresenter;
import com.example.weinfo.view.RegisterView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisterPresenter> implements RegisterView {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_psw)
    EditText etPsw;
    @BindView(R.id.et_repsw)
    EditText etRepsw;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    public static final int TYPE_REGISTER = 0;
    public static final int TYPE_INFO = 1;
    private int type;
    private String uid;
    private String typeId;

    //启动activity全用这个方法启动，要明确参数
    public static void startAct(Context context, int type) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra(Constants.TYPE, type);
        context.startActivity(intent);
    }

    public static void startAct(Context context, int type, String uid, String typeId) {
        Intent intent = new Intent(context, RegisterActivity.class);
        intent.putExtra(Constants.TYPE, type);
        intent.putExtra(Constants.USERID, uid);
        intent.putExtra(Constants.TYPE_ID, typeId);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        type = getIntent().getIntExtra(Constants.TYPE, TYPE_REGISTER);
        uid = getIntent().getStringExtra(Constants.USERID);
        typeId = getIntent().getStringExtra(Constants.TYPE_ID);
        if (type == TYPE_REGISTER) {
            toolbar.setTitle(R.string.register);
        } else {
            toolbar.setTitle(R.string.input_info);
        }
        setSupportActionBar(toolbar);
    }

    @Override
    protected void initPresenter() {
        mPresenter = new RegisterPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    public void showToast(String string) {

    }

    @OnClick({R.id.btn_rzm, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_rzm:
                break;
            case R.id.btn_register:
                register();
                break;
        }
    }

    private void register() {
        String name = etName.getText().toString().trim();
        String psw = etPsw.getText().toString().trim();
        String repsw = etRepsw.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(psw) || TextUtils.isEmpty(repsw)) {
            showToast(BaseApp.getRes().getString(R.string.register_name_or_psw_not_null));
            return;
        }
        if (type == TYPE_REGISTER) {
            mPresenter.register(name, psw);
        } else {
            mPresenter.registerAccess(name, psw, uid, typeId);
        }
    }

    @Override
    public void registerSuccess() {
        //注册成功后只需要退出当前注册页面即可
        finish();
    }

    @Override
    public void loginSuccess() {
        //完善资料后，登录成功，结束当前页面,跳转到主页面
        showToast(BaseApp.getRes().getString(R.string.login_success));
        MainActivity.startAct(this);
        finish();
        //登录页面也应该finish();   (EventBus，广播，handler)
        EventBus.getDefault().post(new FinishEvent());
    }
}