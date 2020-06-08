package com.example.weinfo.view;

import com.example.weinfo.base.BaseView;
import com.example.weinfo.bean.MainBean;

import java.util.ArrayList;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/6   16:41
 **/
public interface MainView extends BaseView {
    void setData(ArrayList<MainBean.DataBean.DatasBean> datasBeans);
}
