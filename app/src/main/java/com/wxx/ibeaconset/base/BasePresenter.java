package com.wxx.ibeaconset.base;

import java.lang.ref.WeakReference;

/**
 * 作者：Tangren_ on 2017/3/6 0006.
 * 邮箱：wu_tangren@163.com
 * TODO:使用弱引用
 */


public abstract class BasePresenter<T> {

    protected WeakReference<T> viewRef;

    public void attachView(T view) {
        viewRef = new WeakReference<T>(view);
    }

    public void detachView() {
        if (viewRef != null) {
            viewRef.clear();
            viewRef = null;
        }
    }
}
