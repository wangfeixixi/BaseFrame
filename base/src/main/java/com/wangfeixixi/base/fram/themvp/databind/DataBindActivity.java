
package com.wangfeixixi.base.fram.themvp.databind;

import android.os.Bundle;

import com.wangfeixixi.base.fram.themvp.model.IModel;
import com.wangfeixixi.base.fram.themvp.presenter.ActivityPresenter;
import com.wangfeixixi.base.fram.themvp.view.IDelegate;

/**
 * 集成数据-视图绑定的Activity基类(Presenter层)
 *
 * @param <T> View层代理类
 */
public abstract class DataBindActivity<T extends IDelegate> extends
        ActivityPresenter<T> {
    protected DataBinder binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binder = getDataBinder();
    }

    public abstract DataBinder getDataBinder();

    public <D extends IModel> void notifyModelChanged(D data) {
        if (binder != null)
            binder.viewBindModel(viewDelegate, data);
    }
}