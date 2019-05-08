package com.wangfeixixi.base.thread;

import android.os.Handler;

public class ThreadUiUtils {
    private static Handler handler = new Handler();

    /**
     * 在主线程执行
     *
     * @param runnable
     */
    public static void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }

    /**
     * 在主线程执行
     *
     * @param runnable
     */
    public static void runOnUiThreadDelayed(Runnable runnable, int a) {
        handler.postDelayed(runnable, a);
    }

    public static void stop() {
        handler.removeCallbacksAndMessages(null);
    }
}
