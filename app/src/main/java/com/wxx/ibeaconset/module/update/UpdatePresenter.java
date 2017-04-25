package com.wxx.ibeaconset.module.update;

import com.wxx.ibeaconset.base.BasePresenter;
import com.wxx.ibeaconset.bean.PageList;

import java.io.InputStream;

/**
 * 作者：Tangren_ on 2017/4/25 0025.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class UpdatePresenter extends BasePresenter<UpdateView> {
    private UpdateView view;
    private UpdateModel model = new UpdateCompl();

    public UpdatePresenter(UpdateView view) {
        this.view = view;
    }

    public void fetchUpdate(PageList.DataBean.PagesBean pagesBean) {
        if (model != null) {
            model.fetchUpdate(new UpdateModel.loadUpdate() {
                @Override
                public void onSuccess(int code, int type, String msg) {
                    if (view != null)
                        view.onSuccess(code, type, msg);
                }

                @Override
                public void onFail(String msg) {
                    if (view != null)
                        view.onFail(msg);
                }
            }, pagesBean);
        }
    }

    public void addImage(InputStream stream) {
        if (model != null) {
            model.addImage(new UpdateModel.loadUpdate() {
                @Override
                public void onSuccess(int code, int type, String msg) {
                    if (view != null) {
                        view.onSuccess(code, type, msg);
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view != null) {
                        view.onFail(msg);
                    }
                }
            }, stream);
        }
    }
}
