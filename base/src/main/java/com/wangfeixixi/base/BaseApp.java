package com.wangfeixixi.base;

import android.app.Application;

import com.wangfeixixi.base.utils.ConfigUtils;
import com.wangfeixixi.base.debug.CrashHandler;

public class BaseApp extends Application {

    private static Application instance;

    public void onCreate() {
        super.onCreate();
        instance = this;
        if (ConfigUtils.isApkInDebug()) {
            CrashHandler.getInstance().init();

        }
    }

    public static Application getInstance() {
        return instance;
    }
}
