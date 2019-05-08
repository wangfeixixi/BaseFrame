package com.wangfeixixi.base.debug;

import android.content.Context;
import android.content.SharedPreferences;

import com.wangfeixixi.base.UIUtils;
import com.wangfeixixi.base.utils.DateUtils;

import java.util.Map;

/**
 * 存储临时变量
 */
public class SpLogUtil {
    private static String mFileName = "TempLog";

    public static void init() {
        String currentDate = DateUtils.getCurrentDate("MM月dd日HH:mm:ss");
        mFileName = currentDate;
    }

    private static SharedPreferences sp;

    /**
     * 写入boolean变量至sp中
     *
     * @param key   存储节点名称
     * @param value 存储节点的值 boolean
     */
    public static void putBoolean(String key, boolean value) {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = UIUtils.getContext().getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        }
        sp.edit().putBoolean(key, value).commit();
    }

    /**
     * 读取boolean标示从sp中
     *
     * @param key      存储节点名称
     * @param defValue 没有此节点默认值
     * @return 默认值或者此节点读取到的结果
     */
    public static boolean getBoolean(String key, boolean defValue) {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = UIUtils.getContext().getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        }
        return sp.getBoolean(key, defValue);
    }

    /**
     * 写入boolean变量至sp中
     *
     * @param key   存储节点名称
     * @param value 存储节点的值string
     */
    public static void putString(String key, String value) {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = UIUtils.getContext().getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        }
        sp.edit().putString(key, value).apply();
    }

    /**
     * 返回所有
     */
    public static Map<String, ?> getAll() {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = UIUtils.getContext().getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        }
        return sp.getAll();
    }

    /**
     * 读取boolean标示从sp中
     *
     * @param key      存储节点名称
     * @param defValue 没有此节点默认值
     * @return 默认值或者此节点读取到的结果
     */
    public static String getString(String key, String defValue) {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = UIUtils.getContext().getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        }
        return sp.getString(key, defValue);
    }

    /**
     * 读取boolean标示从sp中
     *
     * @param key 存储节点名称
     * @return 默认值或者此节点读取到的结果
     */
    public static String getString(String key) {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = UIUtils.getContext().getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        }
        return sp.getString(key, null);
    }

    /**
     * 写入boolean变量至sp中
     *
     * @param key   存储节点名称
     * @param value 存储节点的值string
     */
    public static void putInt(String key, int value) {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = UIUtils.getContext().getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        }
        sp.edit().putInt(key, value).commit();
    }

    /**
     * 读取boolean标示从sp中
     *
     * @param key      存储节点名称
     * @param defValue 没有此节点默认值
     * @return 默认值或者此节点读取到的结果
     */
    public static int getInt(String key, int defValue) {
        //(存储节点文件名称,读写方式)
        if (sp == null) {
            sp = UIUtils.getContext().getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        }
        return sp.getInt(key, defValue);
    }

    /**
     * 从sp中移除指定节点
     *
     * @param key 需要移除节点的名称
     */

    public static void remove(String key) {
        if (sp == null) {
            sp = UIUtils.getContext().getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        }
        sp.edit().remove(key).commit();
    }

    /**
     * 清除所有缓存
     */
    public static void clear() {
        if (sp == null) {
            sp = UIUtils.getContext().getSharedPreferences(mFileName, Context.MODE_PRIVATE);
        }
        sp.edit().clear().apply();
    }
}
