package com.wangfeixixi.base.utils;

import android.content.pm.ApplicationInfo;

import com.wangfeixixi.base.UIUtils;

public class ConfigUtils {

    //判断当前应用是否是debug状态
    public static boolean isApkInDebug() {
        try {
            ApplicationInfo info = UIUtils.getContext().getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}
