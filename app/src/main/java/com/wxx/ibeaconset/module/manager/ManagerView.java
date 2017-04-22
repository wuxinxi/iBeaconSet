package com.wxx.ibeaconset.module.manager;

import com.wxx.ibeaconset.bean.PageList;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public interface ManagerView {
    void onFail(String msg);

    void onPageList(int type, List<PageList.DataBean.PagesBean> pagesBeanList, int total_count);
}
