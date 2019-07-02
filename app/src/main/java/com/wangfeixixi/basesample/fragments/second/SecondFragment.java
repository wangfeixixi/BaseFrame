package com.wangfeixixi.basesample.fragments.second;

import com.wangfeixixi.basesample.base.BaseFragment;

import me.yokeyword.fragmentation.ISupportFragment;

public class SecondFragment extends BaseFragment<SecondDelegate> {
    public static ISupportFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    protected Class<SecondDelegate> getDelegateClass() {
        return SecondDelegate.class;
    }

    @Override
    protected void initData() {

    }
}
