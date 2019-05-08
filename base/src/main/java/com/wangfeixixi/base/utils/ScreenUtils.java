package com.wangfeixixi.base.utils;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.wangfeixixi.base.UIUtils;

public class ScreenUtils {
    public static int getScreenHeight() {
        return UIUtils.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth() {
        return UIUtils.getResources().getDisplayMetrics().widthPixels;
    }

    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, UIUtils.getResources().getDisplayMetrics());
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = UIUtils.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 获取设备最小宽度
     *
     * @param activity activity
     * @return float
     */
    public static float getDeviceMinWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        float density = dm.density;


        int heightPixels = getScreenHeight();
        int widthPixels = getScreenWidth();
        float heightDP = heightPixels / density;
        float widthDP = widthPixels / density;
//        float smallestWidthDP;
//        if (widthDP < heightDP) {
//            smallestWidthDP = widthDP;
//        } else {
//            smallestWidthDP = heightDP;
//        }
        return widthDP < heightDP ? widthDP : heightDP;
    }
}
