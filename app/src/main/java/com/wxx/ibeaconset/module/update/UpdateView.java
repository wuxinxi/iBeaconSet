package com.wxx.ibeaconset.module.update;

/**
 * 作者：Tangren_ on 2017/4/25 0025.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public interface UpdateView {
    void onSuccess(int code, int type, String msg);

    void onFail(String msg);
}
