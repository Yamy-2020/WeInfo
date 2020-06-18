package com.example.weinfo.view;

import com.example.weinfo.base.BaseView;
import com.example.weinfo.bean.ItInfoArticle;
import com.example.weinfo.bean.ItInfoTabBean;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/17   14:07
 **/
public interface ItInfoView extends BaseView {
    void setData(ItInfoTabBean itInfoTabBean);

    void setData2(ItInfoArticle itInfoArticle);
}
