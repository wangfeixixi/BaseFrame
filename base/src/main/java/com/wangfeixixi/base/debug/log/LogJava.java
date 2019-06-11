package com.wangfeixixi.base.debug.log;

import com.alibaba.fastjson.JSON;

/**
 * 用于java运行环境的log
 */
public class LogJava {


    public static void d(Object o) {
        d(Utils.toString(o));
    }

    public static void e(Object o) {
        e(Utils.toString(o));
    }

    public static void bean(Object o) {
        String jsonString = JSON.toJSONString(o);
        dJsonString(jsonString, 2);
    }

    public static void d(String msg) {
        System.out.println(msg);
    }

    public static void e(String msg) {
        System.err.println(msg);
    }

    private static void dJsonString(String json, int indentSpaces) {
        if (Utils.isEmpty(json)) {
            d("Empty/Null json content");
            return;
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                InnerJSONObject jsonObject = new InnerJSONObject(json);
                //设置缩进
                String message = jsonObject.toString(indentSpaces);

                d(message);
                return;
            }
            if (json.startsWith("[")) {
                InnerJSONArray jsonArray = new InnerJSONArray(json);
                String message = jsonArray.toString(indentSpaces);
                d(message);
                return;
            }
            e("Invalid Json");
        } catch (InnerJSONException e) {
            e("Invalid Json");
        }
    }
}
