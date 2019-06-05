package com.wangfeixixi.base.debug;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.wangfeixixi.base.utils.VersionUtils;
import com.wangfeixixi.logxixi.Logger;
import com.wangfeixixi.logxixi.Printer;


public class LogUtils {

    /**
     * Given tag will be used as tag only once for this method call regardless of the tag that's been
     * set during initialization. After this invocation, the general tag that's been set will
     * be used for the subsequent log calls
     */
    public static Printer tag(@Nullable String tag) {
        if (VersionUtils.isApkInDebug())
            return Logger.t(tag);
        else return null;
    }

    /**
     * General log function that accepts all configurations as parameter
     */
    public static void log(int priority, @Nullable String tag, @Nullable String message, @Nullable Throwable throwable) {
        if (VersionUtils.isApkInDebug())
            Logger.log(priority, tag, message, throwable);
    }

    public static void d(@NonNull String message, @Nullable Object... args) {
        if (VersionUtils.isApkInDebug())
            Logger.d(message, args);
    }

    public static void d(@Nullable Object object) {
        if (VersionUtils.isApkInDebug())
            Logger.d(object);
    }

    public static void dJsonString(@Nullable Object object) {
        if (VersionUtils.isApkInDebug())
            Logger.dJsonString(object);
    }

    public static void e(@NonNull String message, @Nullable Object... args) {
        if (VersionUtils.isApkInDebug())
            Logger.e(null, message, args);
    }

    public static void e(@Nullable Throwable throwable, @NonNull String message, @Nullable Object... args) {
        if (VersionUtils.isApkInDebug())
            Logger.e(throwable, message, args);
    }

    public static void i(@NonNull String message, @Nullable Object... args) {
        if (VersionUtils.isApkInDebug())
            Logger.i(message, args);
    }

    public static void v(@NonNull String message, @Nullable Object... args) {
        if (VersionUtils.isApkInDebug())
            Logger.v(message, args);
    }

    public static void w(@NonNull String message, @Nullable Object... args) {
        if (VersionUtils.isApkInDebug())
            Logger.w(message, args);
    }

    /**
     * Tip: Use this for exceptional situations to log
     * ie: Unexpected errors etc
     */
    public static void wtf(@NonNull String message, @Nullable Object... args) {
        if (VersionUtils.isApkInDebug())
            Logger.wtf(message, args);
    }

    /**
     * Formats the given json content and print it
     */
    public static void json(@Nullable String json) {
        if (VersionUtils.isApkInDebug())
            Logger.json(json);
    }

    /**
     * Formats the given xml content and print it
     */
    public static void xml(@Nullable String xml) {
        if (VersionUtils.isApkInDebug())
            Logger.xml(xml);
    }
}
