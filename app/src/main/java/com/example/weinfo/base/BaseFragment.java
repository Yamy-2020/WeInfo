package com.example.weinfo.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.weinfo.util.ToastUtil;
import com.example.weinfo.widget.LoadingDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 项目名称：Mvp
 * 作者：Yamy
 * 创建时间：2020/6/1   22:36
 **/
public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    public P mPresenter;
    private Unbinder bind;
    private LoadingDialog mLoadingDialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayout(), null);
        bind = ButterKnife.bind(this, view);
        initPresenter();
        if (mPresenter != null) {
            mPresenter.bindView(this);
        }
        return view;

    }

    protected abstract void initPresenter();

    protected abstract int getLayout();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initData();
    }

    protected abstract void initData();

    protected abstract void initView(View view);

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.destroy();
        bind.unbind(); //解除butterknife绑定
    }
    @Override
    public void showToast(final String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToastShort(msg);
            }
        });
    }
    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(getContext());
        }
        if (!mLoadingDialog.isShowing()){
            mLoadingDialog.show();
        }
    }

    @Override
    public void hideLoading() {
        if (mLoadingDialog != null ){
            mLoadingDialog.dismiss();
        }
    }
}
