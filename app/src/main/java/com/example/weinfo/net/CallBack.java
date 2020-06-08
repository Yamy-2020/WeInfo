package com.example.weinfo.net;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/6   16:23
 **/
public interface CallBack<T> {
    void onSuccess(T t);
    void onFail(String string);
}
