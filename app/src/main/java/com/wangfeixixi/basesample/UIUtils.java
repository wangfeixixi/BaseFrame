package com.wangfeixixi.basesample;

import android.app.Application;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;


public class UIUtils {

    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }

    public static Resources getResources() {
        return getContext().getResources();
    }

    public static Application getContext() {
        return BaseApp.getInstance();
    }

    public static View inflate(int id) {
        return View.inflate(getContext(), id, (ViewGroup) null);
    }

    /**
     * dimen中无论是dp，还是px
     * 都是返回像素px
     *
     * @param id dime的id
     * @return 返回像素
     */
    public static int getDimens(int id) {
        return getResources().getDimensionPixelSize(id);
    }

    public static String getString(int id) {
        return getResources().getString(id);
    }

    public static Drawable getDrawable(int id) {
        return getResources().getDrawable(id);
    }


}
