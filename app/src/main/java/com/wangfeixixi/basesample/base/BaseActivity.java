package com.wangfeixixi.basesample.base;

import android.widget.Toast;

import com.wangfeixixi.frame.presenter.ActivityPresenter;
import com.wangfeixixi.frame.view.IDelegate;

import me.yokeyword.fragmentation.ISupportFragment;

public abstract class BaseActivity<T extends IDelegate> extends ActivityPresenter<T> {
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    /**
     * 处理回退事件
     *
     * @return
     */
    @Override
    public void onBackPressedSupport() {
        if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            pop();
        } else {
            if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
                finish();
            } else {
                TOUCH_TIME = System.currentTimeMillis();
                Toast.makeText(this, "双击推出", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
