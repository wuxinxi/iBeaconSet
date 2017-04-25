package com.wxx.ibeaconset.module.relation;

import com.wxx.ibeaconset.base.BasePresenter;

/**
 * 作者：Tangren_ on 2017/4/25 0025.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class RelationPresenter extends BasePresenter<RelationView> {
    private RelationView view;
    private RelationModel model = new RelationCompl();

    public RelationPresenter(RelationView view) {
        this.view = view;
    }

    public void fetchRelation(int page_ids, int device_id) {
        if (model != null) {
            model.FetchRelation(new RelationModel.loadRelation() {
                @Override
                public void onSuccess(String msg) {
                    if (view != null) {
                        view.onSuccess(msg);
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view != null) {
                        view.onFail(msg);
                    }
                }
            }, page_ids, device_id);
        }
    }
}
