package com.example.weinfo.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.example.weinfo.util.ToastUtil;
import com.example.weinfo.widget.LoadingDialog;

import butterknife.ButterKnife;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/6   16:34
 **/
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView {
    protected P mPresenter;
    private LoadingDialog mLoadingDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        //避免进入页面软键盘弹出，底部控件被顶起
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        ButterKnife.bind(this);
        initPresenter();
        if (mPresenter != null) {
            mPresenter.bindView(this);
        }
        initView();
        initData();
    }
    protected abstract void initData();

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //界面销毁,取消网络请求
        //解除v层和P的关联
        mPresenter.destroy();
        mPresenter = null;
        hideLoading();
    }
    @Override
    public void showToast(final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToastShort(msg);
            }
        });

    }
    @Override
    public void showLoading() {
        if (mLoadingDialog == null) {
            mLoadingDialog = new LoadingDialog(this);
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
