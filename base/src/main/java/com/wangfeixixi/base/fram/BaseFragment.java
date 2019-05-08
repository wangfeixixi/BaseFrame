package com.wangfeixixi.base.fram;

import com.wangfeixixi.base.fram.themvp.presenter.FragmentPresenter;
import com.wangfeixixi.base.fram.themvp.view.IDelegate;

public abstract class BaseFragment<T extends IDelegate> extends FragmentPresenter<T> {
}
