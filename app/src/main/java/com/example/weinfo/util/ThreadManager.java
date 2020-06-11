package com.example.weinfo.util;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 项目名称：WeInfo
 * 作者：Yamy
 * 创建时间：2020/6/11   13:34
 **/
public class ThreadManager {
    private volatile static ThreadManager sManager = null;
    private final ThreadPoolExecutor mExecutor;

    private ThreadManager() {
        //初始化线程池
        /*int corePoolSize,核心线程的数量,这些线程始终存活
        int maximumPoolSize,最大线程数量
        long keepAliveTime,非核心线程闲下来存活的时间
        TimeUnit unit,存活的时间单位
        BlockingQueue<Runnable> workQueue,排队策略
        ThreadFactory threadFactory 线程工厂,线程怎么创建出来的*/
        mExecutor = new ThreadPoolExecutor(
                3, 5,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(),
                Executors.defaultThreadFactory()
        );
    }

    public static ThreadManager getInstance() {
        if (sManager == null) {
            synchronized (ThreadManager.class) {
                if (sManager == null) {
                    sManager = new ThreadManager();
                }
            }
        }
        return sManager;
    }

    //执行任务
    public void execute(Runnable runnable){
        mExecutor.execute(runnable);
    }

    public void remove(Runnable runnable){
        mExecutor.remove(runnable);
    }
}
