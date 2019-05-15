package com.wangfeixixi.base.utils;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.wangfeixixi.base.UIUtils;

public class VersionUtils {

    //判断当前应用是否是debug状态
    public static boolean isApkInDebug() {
        try {
            ApplicationInfo info = UIUtils.getContext().getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getLastUpdateTime() {
        String lastDate = null;
        try {
            PackageManager packageManager = UIUtils.getContext().getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(UIUtils.getContext().getPackageName(), 0);
            //应用最后一次更新时间
            long lastUpdateTime = packageInfo.lastUpdateTime;
            lastDate = DateUtils.getStringByFormat(lastUpdateTime, DateUtils.dateFormatYMDHMS);
        } catch (PackageManager.NameNotFoundException e) {
            return e.getMessage();
        }
        return lastDate;
    }


    public static String getFirstInstallTime() {
        PackageManager packageManager = UIUtils.getContext().getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(UIUtils.getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            return e.getMessage();
        }
        //应用装时间
        long firstInstallTime = packageInfo.firstInstallTime;
        return DateUtils.getStringByFormat(firstInstallTime, DateUtils.dateFormatYMDHMS);
    }


    public static int getVersionCode() {
        PackageManager manager = UIUtils.getContext().getPackageManager();
        int code = 0;
        try {
            PackageInfo info = manager.getPackageInfo(UIUtils.getContext().getPackageName(), 0);
            code = info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return code;
    }

    public static String getVersionName() {
        PackageManager manager = UIUtils.getContext().getPackageManager();
        String name = null;
        try {
            PackageInfo info = manager.getPackageInfo(UIUtils.getContext().getPackageName(), 0);
            name = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return name;
    }
}
