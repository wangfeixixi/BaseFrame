package com.wangfeixixi.basesample.fragments.first;

import com.wangfeixixi.basesample.R;
import com.wangfeixixi.basesample.base.BaseDelegate;

public class FirstDelegate extends BaseDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.first_fragment;
    }

    public void test() {

        toast("asdfa");
    }
}
