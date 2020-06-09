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

import com.example.weinfo.R;
import com.example.weinfo.base.BaseActivity;
import com.example.weinfo.base.BaseApp;
import com.example.weinfo.presenter.RegisterPresenter;
import com.example.weinfo.view.RegisterView;

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

    //启动activity全用这个方法启动，要明确参数
    public static void startAct(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        toolbar.setTitle(R.string.register);
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
        mPresenter.register(name, psw);
    }
    @Override
    public void registerSuccess() {
        //注册成功后只需要退出当前注册页面即可
        finish();
    }
}