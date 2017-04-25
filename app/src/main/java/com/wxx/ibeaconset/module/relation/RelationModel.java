package com.wxx.ibeaconset.module.relation;

/**
 * 作者：Tangren_ on 2017/4/25 0025.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public interface RelationModel {

    void FetchRelation(loadRelation listener, int page_ids, int device_id);

    interface loadRelation {
        void onSuccess(String msg);

        void onFail(String msg);
    }

}
