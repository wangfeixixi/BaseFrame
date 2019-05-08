package com.wangfeixixi.base.thread;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolManager {

    private ThreadPoolProxy longPoolProxy;
    private ThreadPoolProxy netPoolProxy;

    private ThreadPoolManager() {
    }

    private static class AAA {
        private static ThreadPoolManager a = new ThreadPoolManager();
    }

    // 懒汉式   饿汉式(线程安全的)

    public static ThreadPoolManager getInstance() {
        return AAA.a;
    }


    /**
     * 1. 读写文件
     * 2. 请求服务器数据
     * 效率最高线程池
     * cpu * 2 + 1
     *
     * @return
     */
    public ThreadPoolProxy createLongThreadPool() {
        if (longPoolProxy == null) {
            longPoolProxy = new ThreadPoolProxy(10, 10, 5000);
        }
        return longPoolProxy;
    }


    /**
     * 1. 读写文件
     * 2. 请求服务器数据
     * 效率最高线程池
     * cpu * 2 + 1
     *
     * @return
     */
    public ThreadPoolProxy createNetThreadPool() {
        if (netPoolProxy == null)
            netPoolProxy = new ThreadPoolProxy(50, 50, 5000);
        return netPoolProxy;
    }


    public class ThreadPoolProxy {
        ThreadPoolExecutor threadPool;
        private int corePoolSize;
        private int maximumPoolSize;
        private long keepAliveTime;

        public ThreadPoolProxy(int corePoolSize, int maximumPoolSize, long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;
        }

        /**
         * 执行线程任务
         *
         * @param runnable
         */
        public void execture(Runnable runnable) {
            if (threadPool == null) {
                //corePoolSize : c初始化的线程数量
                //maximumPoolSize: 额外创建的线程数量
                //keepAliveTime
                threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
                        keepAliveTime, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10));
            }

            threadPool.execute(runnable);
        }

        /**
         * 取消线程任务
         *
         * @param runnable
         */
        public void cancel(Runnable runnable) {
            if (threadPool != null && !threadPool.isShutdown() && !threadPool.isTerminated()) {
                threadPool.remove(runnable);
            }
        }
    }
}