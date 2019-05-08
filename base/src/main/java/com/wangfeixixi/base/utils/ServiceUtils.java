package com.wangfeixixi.base.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import com.wangfeixixi.base.UIUtils;

import java.util.List;

public class ServiceUtils {
    /**
     * 判断Service是否正在运行
     *
     * @param serviceName Service 类全名
     * @return true 表示正在运行，false 表示没有运行
     */
    public static boolean isServiceRunning(String serviceName) {
        ActivityManager manager = (ActivityManager) UIUtils.getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceInfoList = manager.getRunningServices(200);
        if (serviceInfoList.size() <= 0) {
            return false;
        }
        for (ActivityManager.RunningServiceInfo info : serviceInfoList) {
            if (info.service.getClassName().equals(serviceName)) {
                return true;
            }
        }
        return false;
    }

    public static void startService(Class clazz) {
        if (!isServiceRunning(clazz.getName()))
            UIUtils.getContext().startService(new Intent(UIUtils.getContext(), clazz));
    }

    public static void stopService(Class clazz) {
        if (isServiceRunning(clazz.getName()))
            UIUtils.getContext().stopService(new Intent(UIUtils.getContext(), clazz));
    }
}
