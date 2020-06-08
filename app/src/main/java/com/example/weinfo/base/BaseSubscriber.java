package com.example.weinfo.base;

import com.bumptech.glide.load.HttpException;
import com.example.weinfo.R;
import com.example.weinfo.util.LogUtil;
import com.example.weinfo.util.ToastUtil;
import com.google.gson.JsonParseException;

import org.json.JSONException;

import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.UnknownHostException;
import java.text.ParseException;

import io.reactivex.subscribers.ResourceSubscriber;

public abstract class BaseSubscriber<T> extends ResourceSubscriber<T> {

    @Override
    public void onError(Throwable e) {
        LogUtil.print("error:" + e.toString());
        String err = "";
//对异常进行分类,不同的异常提示用户不同的信息
        if (e instanceof HttpException) {
            //   HTTP错误
            err = BaseApp.getRes().getString(R.string.net_error);
        } else if (e instanceof ConnectException
                || e instanceof UnknownHostException) {
            //   连接错误
            err = BaseApp.getRes().getString(R.string.conn_error);
        } else if (e instanceof InterruptedIOException) {
            //  连接超时
            err = BaseApp.getRes().getString(R.string.conn_timeout);
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException) {
            //  解析错误
            err = BaseApp.getRes().getString(R.string.parse_error);
        } else {
            err = BaseApp.getRes().getString(R.string.unknow_error);
        }
        onFail(err);
    }

    //如果异常不做特殊处理的话,不需要再次复写这个方法
    //如果需要单独处理的异常,复写这个方法
    protected void onFail(String err) {
        ToastUtil.showToastShort(err);
    }

    @Override
    public void onComplete() {

    }
}
