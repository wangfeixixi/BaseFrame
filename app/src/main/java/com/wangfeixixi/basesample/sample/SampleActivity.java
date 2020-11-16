package com.wangfeixixi.basesample.sample;

import com.trello.rxlifecycle2.android.ActivityEvent;
import com.wangfeixixi.basesample.R;
import com.wangfeixixi.frame.base.BaseActivity;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Created by 鬼谷  on  2020/11/16 16:05
 */
public class SampleActivity extends BaseActivity<SampleDelegate> {
    @Override
    protected Class<SampleDelegate> getDelegateClass() {
        return SampleDelegate.class;
    }


    @Override
    public void init() {
        viewDelegate.setText("hahaha");
        viewDelegate.setOnClickListener(v -> {
            switch (v.getId()) {
                case R.id.btn_second:

                    break;
            }
        });


        Observable
                .create(new ObservableOnSubscribe<Integer>() {
                    @Override
                    public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Exception {
                        //处理完结果发射
                        emitter.onNext(2);
                    }
                })
                .compose(this.bindUntilEvent(ActivityEvent.DESTROY)) //绑定生命周期防止泄漏
                .observeOn(AndroidSchedulers.mainThread()) //在主线程发射
                .subscribeOn(Schedulers.io()) //在子线程工作
                .doOnNext(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        //主线程处理
                    }
                })
                .subscribe();

    }
}
