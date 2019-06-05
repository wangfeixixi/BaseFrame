package com.wangfeixixi.base;

import android.app.Application;

import com.wangfeixixi.base.debug.CrashHandler;
import com.wangfeixixi.base.utils.VersionUtils;
import com.wangfeixixi.logxixi.AndroidLogAdapter;
import com.wangfeixixi.logxixi.Logger;

public class BaseApp extends Application {

    private static Application instance;

    public void onCreate() {
        super.onCreate();
        instance = this;
        if (VersionUtils.isApkInDebug()) {
            CrashHandler.getInstance().init();
            Logger.addLogAdapter(new AndroidLogAdapter());
        }
    }

    public static Application getInstance() {
        return instance;
    }
}
