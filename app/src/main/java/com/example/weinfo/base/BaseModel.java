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
        //销毁网络请求并且取消订阅关系
        if (compositeDisposable != null && compositeDisposable.size()>0){
            //1.调用容器中的所有的Disposable对象dispose();
            //2.会将容器清空
            compositeDisposable.clear();
        }
    }
}
