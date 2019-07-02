package com.wangfeixixi.basesample.activity;

import com.wangfeixixi.basesample.R;
import com.wangfeixixi.basesample.base.BaseActivity;
import com.wangfeixixi.basesample.fragments.first.FirstFragment;

import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

public class MainAppActivity extends BaseActivity<MainAppDelegate> {

    @Override
    protected void initData() {
        if (findFragment(FirstFragment.class) == null) {
            loadRootFragment(R.id.fl_content, FirstFragment.newInstance());
        }
        viewDelegate.get(R.id.fl_content);
    }

    @Override
    protected Class<MainAppDelegate> getDelegateClass() {
        return MainAppDelegate.class;
    }


    @Override
    public void onBackPressedSupport() {
        // 对于 4个类别的主Fragment内的回退back逻辑,已经在其onBackPressedSupport里各自处理了
        super.onBackPressedSupport();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        // 设置横向(和安卓4.x动画相同)
        return new DefaultHorizontalAnimator();
    }

}
