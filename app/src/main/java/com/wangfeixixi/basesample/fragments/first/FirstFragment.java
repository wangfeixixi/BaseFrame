package com.wangfeixixi.basesample.fragments.first;

import com.wangfeixixi.basesample.R;
import com.wangfeixixi.basesample.base.BaseFragment;
import com.wangfeixixi.basesample.fragments.second.SecondFragment;

import me.yokeyword.fragmentation.ISupportFragment;

public class FirstFragment extends BaseFragment<FirstDelegate> {
    public static ISupportFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    protected Class<FirstDelegate> getDelegateClass() {
        return FirstDelegate.class;
    }

    @Override
    protected void initData() {
        viewDelegate.setOnClickListener(v -> start(SecondFragment.newInstance()), R.id.btn_second);

    }
}
