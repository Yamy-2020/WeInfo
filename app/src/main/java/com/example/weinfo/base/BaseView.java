package com.example.weinfo.base;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/6   16:24
 **/
public interface BaseView {
    void showToast(String string);
    //弹loading
    void showLoading();

    //隐藏loading
    void hideLoading();
}
