package com.wangfeixixi.base.fram.themvp.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * View delegate base class
 * 视图层代理的接口协议
 *
 * @author kymjs (http://www.kymjs.com/) on 10/23/15.
 */
public interface IDelegate {
    void create(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

    int getOptionsMenuId();

    Toolbar getToolbar();

    View getRootView();

    void initWidget();
}
