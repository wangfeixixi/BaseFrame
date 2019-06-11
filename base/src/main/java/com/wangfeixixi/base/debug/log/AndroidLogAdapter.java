package com.wangfeixixi.base.debug.log;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.wangfeixixi.base.utils.VersionUtils;

/**
 * Android terminal d output implementation for {@link LogAdapter}.
 * <p>
 * Prints output to LogCat with pretty borders.
 *
 * <pre>
 *  ┌──────────────────────────
 *  │ Method stack history
 *  ├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄
 *  │ Log message
 *  └──────────────────────────
 * </pre>
 */
public class AndroidLogAdapter implements LogAdapter {

    @NonNull
    private final FormatStrategy formatStrategy;

    public AndroidLogAdapter(String tag) {
        this.formatStrategy = PrettyFormatStrategy.newBuilder().tag(tag).build();
    }

    public AndroidLogAdapter(@NonNull FormatStrategy formatStrategy) {
        this.formatStrategy = Utils.checkNotNull(formatStrategy);
    }

    @Override
    public boolean isLoggable(int priority, @Nullable String tag) {
        return VersionUtils.isApkInDebug();
    }

    @Override
    public void log(int priority, @Nullable String tag, @NonNull String message) {
        formatStrategy.log(priority, tag, message);
    }

}
