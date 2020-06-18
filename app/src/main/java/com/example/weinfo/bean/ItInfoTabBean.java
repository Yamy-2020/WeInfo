package com.example.weinfo.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/17   14:20
 **/
public class ItInfoTabBean implements Serializable {

    /**
     * data : [{"children":[],"courseId":13,"id":408,"name":"鸿洋","order":190000,"parentChapterId":407,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":409,"name":"郭霖","order":190001,"parentChapterId":407,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":410,"name":"玉刚说","order":190002,"parentChapterId":407,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":411,"name":"承香墨影","order":190003,"parentChapterId":407,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":413,"name":"Android群英传","order":190004,"parentChapterId":407,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":414,"name":"code小生","order":190005,"parentChapterId":407,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":415,"name":"谷歌开发者","order":190006,"parentChapterId":407,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":416,"name":"奇卓社","order":190007,"parentChapterId":407,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":417,"name":"美团技术团队","order":190008,"parentChapterId":407,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":420,"name":"GcsSloop","order":190009,"parentChapterId":407,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":421,"name":"互联网侦察","order":190010,"parentChapterId":407,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":427,"name":"susion随心","order":190011,"parentChapterId":407,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":428,"name":"程序亦非猿","order":190012,"parentChapterId":407,"userControlSetTop":false,"visible":1},{"children":[],"courseId":13,"id":434,"name":"Gityuan","order":190013,"parentChapterId":407,"userControlSetTop":false,"visible":1}]
     * errorCode : 0
     * errorMsg :
     */

    private int errorCode;
    private String errorMsg;
    private ArrayList<DataBean> data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ArrayList<DataBean> getData() {
        return data;
    }

    public void setData(ArrayList<DataBean> data) {
        this.data = data;
    }


}
