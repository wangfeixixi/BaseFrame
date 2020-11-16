package com.wangfeixixi.frame.base;

import com.wangfeixixi.frame.base.themvp.presenter.ActivityPresenter;


public abstract class BaseActivity<T extends BaseDelegate> extends ActivityPresenter<T> {
    public abstract void init();
}
