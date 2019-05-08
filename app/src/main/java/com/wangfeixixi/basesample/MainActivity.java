package com.wangfeixixi.basesample;

import com.wangfeixixi.base.fram.BaseActivity;

public class MainActivity extends BaseActivity<MainDelegate> {

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }
}
