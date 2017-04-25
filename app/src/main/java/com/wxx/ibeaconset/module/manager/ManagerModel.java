package com.wxx.ibeaconset.module.manager;

import com.wxx.ibeaconset.bean.PageList;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public interface ManagerModel {

    void loadPageList(OnPageListListener listListener, String token, int begin);

    interface OnPageListListener {
        void onSuccess(int type, List<PageList.DataBean.PagesBean> pagesBeanList, int total_count);

        void onFail(String msg);
    }

    void loadDel(OnDelListener listener, int page_id, int postion,List<PageList.DataBean.PagesBean> list);

    interface OnDelListener {
        void delResult(int postion, int code, String msg,List<PageList.DataBean.PagesBean> list);
    }

}
