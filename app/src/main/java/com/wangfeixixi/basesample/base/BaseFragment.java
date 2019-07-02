package com.wangfeixixi.basesample.base;

import com.wangfeixixi.frame.presenter.FragmentPresenter;
import com.wangfeixixi.frame.view.IDelegate;

public abstract class BaseFragment<T extends IDelegate> extends FragmentPresenter<T> {
}
