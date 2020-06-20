package com.example.weinfo.util;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/19   10:29
 * <p>
 * 2
 * 使用这个类需要把style设置成日夜间的样式:Theme.AppCompat.DayNight.NoActionBar
 * 3
 */
/**
 2
 * 使用这个类需要把style设置成日夜间的样式:Theme.AppCompat.DayNight.NoActionBar
 3
 */

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;

import com.example.weinfo.base.BaseApp;
import com.example.weinfo.base.Constants;

public class UiModeUtil {

    /**
     * 夜间模式切换,切换选项时调用
     */
    public static void changeModeUI(AppCompatActivity activity) {
        //获取当前的模式是那种
        int currentNightMode = activity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if (currentNightMode == Configuration.UI_MODE_NIGHT_NO) {
            //日间模式,切成夜间模式
            activity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            //用户设置的日夜模式保持在sp中，
            SpUtil.setParam(Constants.MODE, AppCompatDelegate.MODE_NIGHT_YES);
            BaseApp.mMode = AppCompatDelegate.MODE_NIGHT_YES;
        } else {
            //
            activity.getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            SpUtil.setParam(Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
            BaseApp.mMode = AppCompatDelegate.MODE_NIGHT_NO;
        }
    }

    /**
     25
     * 设置当前的模式
     26
     * @param activity
    27
     */
    public static void setActModeFromSp(AppCompatActivity activity) {
        int mode = (int) SpUtil.getParam(Constants.MODE, AppCompatDelegate.MODE_NIGHT_NO);
        activity.getDelegate().setLocalNightMode(mode);
    }

    /**
     * 设置当前的模式
     * @param activity
     */
    public static void setActModeUseMode(AppCompatActivity activity, int mode) {
        activity.getDelegate().setLocalNightMode(mode);
    }

    public static void setAppMode(int mode) {
        AppCompatDelegate.setDefaultNightMode(mode);
    }
}
