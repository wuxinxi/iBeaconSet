package com.wxx.ibeaconset.module.manager;

import com.wxx.ibeaconset.base.BasePresenter;
import com.wxx.ibeaconset.bean.PageList;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class ManagerPresenter extends BasePresenter<ManagerView> {

    private ManagerView view;
    private ManagerModel model = new ManagerCompl();

    public ManagerPresenter(ManagerView view) {
        this.view = view;
    }

    public void fetchPageList(String token, int begin) {
        if (model != null) {
            model.loadPageList(new ManagerModel.OnPageListListener() {
                @Override
                public void onSuccess(int type, List<PageList.DataBean.PagesBean> pagesBeanList, int total_count) {
                    if (view != null) {
                        view.onPageList(type, pagesBeanList, total_count);
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view != null)
                        view.onFail(msg);
                }
            }, token, begin);
        }
    }


    public void fetchDel(int page_id, int postion,List<PageList.DataBean.PagesBean> list) {
        if (model != null) {
            model.loadDel(new ManagerModel.OnDelListener() {
                @Override
                public void delResult(int postion, int code, String msg,List<PageList.DataBean.PagesBean> list) {
                    if (view != null)
                        view.delResult(postion, code, msg,list);
                }
            }, page_id, postion,list);
        }
    }
}
