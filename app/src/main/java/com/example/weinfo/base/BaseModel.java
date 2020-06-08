package com.example.weinfo.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/6   16:25
 **/
public class BaseModel {
    private CompositeDisposable compositeDisposable = null;

    public void addDisposable(Disposable disposable) {
        if (compositeDisposable != null) {
            compositeDisposable = new CompositeDisposable();
        }
    }

    public void destory() {
        compositeDisposable.clear();
    }
}
