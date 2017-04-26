package com.wxx.ibeaconset.module.update;

import com.wxx.ibeaconset.bean.PageList;

import java.io.InputStream;

/**
 * 作者：Tangren_ on 2017/4/25 0025.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public interface UpdateModel {
    void fetchUpdate(loadUpdate listener, PageList.DataBean.PagesBean pagesBean,String activity);

    interface loadUpdate {
        void onSuccess(int code, int type, String msg);

        void onFail(String msg);
    }

    void addImage(loadUpdate listener, InputStream stream);

}
