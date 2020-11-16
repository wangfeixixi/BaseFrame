package com.wangfeixixi.basesample.sample;

import android.widget.TextView;

import com.wangfeixixi.basesample.R;
import com.wangfeixixi.frame.base.BaseDelegate;

/**
 * @author Created by 鬼谷  on  2020/11/16 16:05
 */
public class SampleDelegate extends BaseDelegate {
    @Override
    public int getRootLayoutId() {
        return R.layout.sample_activity;
    }

    public void setText(String name) {
        TextView view = get(R.id.tv);
        view.setText(name);
    }
}
