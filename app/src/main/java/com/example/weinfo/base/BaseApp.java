package com.example.weinfo.base;

import android.app.Application;
import android.content.res.Resources;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

//5eddda6f570df3d3a0000129
//app一上来会先走application是有条件的,要求app原来所在的进程被杀死才会走,
//如果仅仅是activity销毁了,不一定走
//Android 系统为了提高app启动的速度,在界面销毁之后,进程不会被杀死, 而是变成一个空进程
public class BaseApp extends Application {
    public static BaseApp sContext;

    public static Resources getRes() {
        return sContext.getResources();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
        initUmeng();
    }
    //友盟初始化
    private void initUmeng() {
        //设置要打log
        UMConfigure.setLogEnabled(true);
        //参数
        //上下文
        //友盟的appkey
        //渠道号
        //设备种类
        //做友盟推送时需要
        UMConfigure.init(this, "5eddda6f570df3d3a0000129",
                "umeng", UMConfigure.DEVICE_TYPE_PHONE, "");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        //App Key：2291845489
        //App Secret：e9ca8562ac44f77c4835367d832f3f89
        PlatformConfig.setSinaWeibo("2291845489", "e9ca8562ac44f77c4835367d832f3f89","http://sns.whalecloud.com");
        //
        //
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
    }

    public static BaseApp getInstance() {
        return sContext;
    }

}
