package com.example.weinfo.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 项目名称：Mvp
 * 作者：Yamy
 * 创建时间：2020/6/2   11:41
 **/
public class TimeUtil {
    /**
     * 把一个calender对象转换成8位日期,20200302
     *
     * @param calendar 日历对象
     * @return
     */
    public static String parseTime(Calendar calendar) {
        //格式化日期的
        //pattern:日期的格式
        //年(year):y表示,一般两位或者四位
        //月(month):M表示,一般2位
        //日(day):d表示,一般两位
        //小时(hours):h表示,一般2位
        //分钟(minutes):m表示,一般2位
        //秒(seconds):s表示,一般两位
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        //SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd:hh:mm:ss");
        return format.format(calendar.getTime());
    }
    //判断传过来的日期是啥时候
    public static boolean checkTimeIsTotay(String time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date = new Date();
        String s = format.format(date);
        if (s.equals(time)) {
            return true;
        } else {
            return false;
        }
    }
}
