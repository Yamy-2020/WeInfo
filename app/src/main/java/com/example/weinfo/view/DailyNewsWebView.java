package com.example.weinfo.view;

import com.example.weinfo.base.BaseView;
import com.example.weinfo.bean.DailyNewsBean;
import com.example.weinfo.bean.NewsDailyBean;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   21:38
 **/
public interface DailyNewsWebView extends BaseView {
    void setData(NewsDailyBean newsDailyBean);
}
