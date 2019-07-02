package com.wangfeixixi.frame.databind;

import android.os.Bundle;
import android.view.View;

import com.wangfeixixi.frame.model.IModel;
import com.wangfeixixi.frame.presenter.FragmentPresenter;
import com.wangfeixixi.frame.view.IDelegate;

/**
 * 集成数据-视图绑定的Fragment基类(Presenter层)
 *
 * @param <T> View层代理类
 */
public abstract class DataBindFragment<T extends IDelegate> extends
        FragmentPresenter<T> {

    protected DataBinder binder;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binder = getDataBinder();
    }

    public abstract DataBinder getDataBinder();

    public <D extends IModel> void notifyModelChanged(D data) {
        if (binder != null)
            binder.viewBindModel(viewDelegate, data);
    }
}
