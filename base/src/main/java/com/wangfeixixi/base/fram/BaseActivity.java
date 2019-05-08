package com.wangfeixixi.base.fram;

import com.wangfeixixi.base.fram.themvp.presenter.ActivityPresenter;
import com.wangfeixixi.base.fram.themvp.view.IDelegate;

public abstract class BaseActivity<T extends IDelegate> extends ActivityPresenter<T> {
}
