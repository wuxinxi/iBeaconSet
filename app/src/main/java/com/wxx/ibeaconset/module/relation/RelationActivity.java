package com.wxx.ibeaconset.module.relation;

import com.wxx.ibeaconset.R;
import com.wxx.ibeaconset.base.BaseActivity;

/**
 * 作者：Tangren_ on 2017/4/25 0025.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class RelationActivity extends BaseActivity<RelationView, RelationPresenter> implements RelationView {

    @Override
    protected RelationPresenter createPresenter() {
        return new RelationPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_relation;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onFail(String msg) {

    }
}
