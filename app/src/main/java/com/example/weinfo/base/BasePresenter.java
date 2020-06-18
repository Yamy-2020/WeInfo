package com.example.weinfo.base;

import java.util.ArrayList;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/6   16:28
 **/
public abstract class BasePresenter<V extends BaseView> {

    public V mView;
    public ArrayList<BaseModel> models = new ArrayList<>();

    public BasePresenter() {
        initModel();
    }

    protected abstract void initModel();

    public void bindView(V view) {
        mView = view;
    }

    public void addModel(BaseModel baseModel) {
        models.add(baseModel);
    }

    public void destroy() {
        mView = null;
        for (int i = 0; i < models.size(); i++) {
            BaseModel baseModel = models.get(i);
            baseModel.destory();
        }
    }
}
