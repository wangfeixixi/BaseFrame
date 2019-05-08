package com.wangfeixixi.base.thread;


/**
 * wf
 * 1. 子线程执行方法
 * 2. 切换到主线程
 */
public class ThreadUtils {

    /**
     * 在子线程执行
     *
     * @param runnable
     */
    public static void runOnLongBackThread(Runnable runnable) {
        ThreadPoolManager.getInstance().createLongThreadPool().execture(runnable);
    }

    /**
     * 联网线程
     * 在子线程执行
     *
     * @param runnable
     */
    public static void runOnNetThread(Runnable runnable) {
        ThreadPoolManager.getInstance().createNetThreadPool().execture(runnable);
    }
}