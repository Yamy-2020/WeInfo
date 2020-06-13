package com.example.weinfo.ui.fragment;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.weinfo.R;
import com.example.weinfo.base.BaseFragment;
import com.example.weinfo.presenter.DiscoveryPresenter;
import com.example.weinfo.view.DiscoveryView;
import com.hyphenate.easeui.widget.EaseTitleBar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class DiscoveryFragment extends BaseFragment<DiscoveryPresenter> implements DiscoveryView {

    @BindView(R.id.title_bar)
    EaseTitleBar titleBar;
    @BindView(R.id.et_friend)
    EditText etFriend;
    @BindView(R.id.iv_add_friend)
    ImageView ivAddFriend;
    @BindView(R.id.cl_add)
    ConstraintLayout clAdd;
    @BindView(R.id.et_create_group)
    EditText etCreateGroup;
    @BindView(R.id.iv_create_group)
    ImageView ivCreateGroup;
    @BindView(R.id.cl_create_group)
    ConstraintLayout clCreateGroup;
    @BindView(R.id.et_group)
    EditText etGroup;
    @BindView(R.id.iv_add_group)
    ImageView ivAddGroup;
    @BindView(R.id.cl_add_group)
    ConstraintLayout clAddGroup;
    @BindView(R.id.iv_zhihu)
    ImageView ivZhihu;
    @BindView(R.id.tv_zhihu)
    TextView tvZhihu;
    @BindView(R.id.cl_zhihu)
    ConstraintLayout clZhihu;
    @BindView(R.id.iv_it)
    ImageView ivIt;
    @BindView(R.id.tv_it)
    TextView tvIt;
    @BindView(R.id.cl_it_info)
    ConstraintLayout clItInfo;
    @BindView(R.id.iv_tencent)
    ImageView ivTencent;
    @BindView(R.id.tv_tencent)
    TextView tvTencent;
    @BindView(R.id.cl_tencent)
    ConstraintLayout clTencent;
    @BindView(R.id.btn_logout)
    Button btnLogout;
    Unbinder unbinder;

    @Override
    protected void initPresenter() {
        mPresenter = new DiscoveryPresenter();
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_discovery;
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

    @OnClick({R.id.iv_add_friend, R.id.cl_add, R.id.iv_create_group, R.id.cl_create_group, R.id.iv_add_group, R.id.cl_add_group, R.id.btn_logout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_add_friend:
                addFriend();
                break;
            case R.id.iv_create_group:
                createGroup();
                break;
            case R.id.iv_add_group:
                addGroup();
                break;
            case R.id.cl_zhihu:
                break;
            case R.id.cl_it_info:
                break;
            case R.id.cl_tencent:
                break;
            case R.id.btn_logout:
                break;
        }
    }

    private void addGroup() {
        String group = etGroup.getText().toString().trim();
        if (TextUtils.isEmpty(group)) {
            showToast("请输入群id");
            return;
        }
        mPresenter.addGroup(group);
    }

    private void createGroup() {
        String name = etCreateGroup.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showToast("请输入群名称");
            return;
        }
        mPresenter.createGroup(name);
    }

    private void addFriend() {
        String friendName = etFriend.getText().toString().trim();
        if (TextUtils.isEmpty(friendName)) {
            showToast("请输入好友id");
            return;
        }
        mPresenter.addFriend(friendName);
    }
}