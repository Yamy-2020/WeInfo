package com.example.weinfo.ui.fragment;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.weinfo.R;
import com.example.weinfo.base.BaseFragment;
import com.example.weinfo.presenter.MePresenter;
import com.example.weinfo.view.MeView;
import com.hyphenate.easeui.ui.MapActivity;
import com.hyphenate.easeui.widget.EaseTitleBar;

import butterknife.BindView;
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

    @Override
    protected void initPresenter() {

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

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.cl_setting,R.id.cl_map,R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cl_setting:

                break;
            case R.id.cl_map:
                startActivity(new Intent(getActivity(), MapActivity.class));
                break;
            case R.id.btn_logout:
                logout();
                break;
        }
    }

    private void logout() {

    }
}