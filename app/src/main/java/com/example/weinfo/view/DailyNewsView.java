package com.example.weinfo.view;

import com.example.weinfo.base.BaseView;
import com.example.weinfo.bean.DailyNewsBean;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/16   10:11
 **/
public interface DailyNewsView extends BaseView {
    void setData(DailyNewsBean dailyNewsBean);
}
