package com.example.weinfo.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weinfo.R;
import com.example.weinfo.base.BaseFragment;
import com.example.weinfo.presenter.MePresenter;
import com.example.weinfo.ui.activity.LoginActivity;
import com.example.weinfo.util.UiModeUtil;
import com.example.weinfo.view.MeView;
import com.hyphenate.easeui.widget.EaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MeFragment extends BaseFragment<MePresenter> implements MeView {

    @BindView(R.id.title_bar)
    EaseTitleBar titleBar;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.tv_setting)
    TextView tvSetting;
    @BindView(R.id.cl_setting)
    ConstraintLayout clSetting;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    Unbinder unbinder;
    @BindView(R.id.iv_map)
    ImageView ivMap;
    @BindView(R.id.tv_map)
    TextView tvMap;
    @BindView(R.id.cl_map)
    ConstraintLayout clMap;
    @BindView(R.id.iv_collect)
    ImageView ivCollect;
    @BindView(R.id.tv_collect)
    TextView tvCollect;
    @BindView(R.id.cl_collect)
    ConstraintLayout clCollect;
    Unbinder unbinder1;
    @BindView(R.id.sc)
    SwitchCompat sc;
    @BindView(R.id.cl_sc)
    ConstraintLayout clSc;
    Unbinder unbinder2;

    @Override
    protected void initPresenter() {
        mPresenter = new MePresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        sc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isPressed()) {
                    AppCompatActivity activity = (AppCompatActivity) getActivity();
                    //切换夜间模式，activity会重建
                    UiModeUtil.changeModeUI(activity);
                }
            }
        });
    }

    @OnClick({R.id.cl_setting, R.id.cl_map, R.id.btn_logout, R.id.cl_collect, R.id.sc})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cl_setting:
                setting();
                break;
            case R.id.cl_map:
                break;
            case R.id.cl_collect:

                break;
            case R.id.btn_logout:
                logout();
                break;
            case R.id.sc:
                break;
        }
    }

    private void setting() {

    }

    private void logout() {
        mPresenter.logout();
    }

    @Override
    public void logoutSuccess() {
        showToast("退出登录成功");
        getActivity().finish();
        startActivity(new Intent(getActivity(), LoginActivity.class));
    }
}