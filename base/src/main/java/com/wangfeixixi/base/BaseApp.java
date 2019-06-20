package com.wangfeixixi.base;

import android.app.Application;

public class BaseApp extends Application {

    private static Application instance;

    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static Application getInstance() {
        return instance;
    }
}
