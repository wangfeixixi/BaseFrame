package com.wangfeixixi.basesample;

import android.os.Bundle;

import com.wangfeixixi.base.fram.BaseActivity;

public class MainActivity extends BaseActivity<MainDelegate> {

    @Override
    protected Class<MainDelegate> getDelegateClass() {
        return MainDelegate.class;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);





    }
}
