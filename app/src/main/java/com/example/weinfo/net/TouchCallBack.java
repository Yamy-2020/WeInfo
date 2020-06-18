package com.example.weinfo.net;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/18   11:53
 **/
public interface TouchCallBack {
    void onItemMove(int fromPosition,int toPosition);
    void onItemDelete(int position);
}
