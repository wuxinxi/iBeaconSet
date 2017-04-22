package com.wxx.ibeaconset.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.wxx.ibeaconset.R;

import butterknife.ButterKnife;

/**
 * 作者：Tangren_ on 2017/3/6 0006.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public abstract class BaseActivity<V, T extends BasePresenter<V>> extends AppCompatActivity {

    protected T mPresenter;

    protected abstract T createPresenter();

    protected abstract int initLayout();

    protected abstract void initView();

    protected abstract void initData();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        ButterKnife.bind(this);
        initView();
        mPresenter = createPresenter();
        if (mPresenter != null)
            mPresenter.attachView((V) this);
        initData();
    }

    //启动进度条
    public void showLoading() {
    }

    //取消进度条
    public void disLoading() {

    }


    protected void startActivityFromRight(Intent intent, int requestCode) {
        startActivityForResult(intent, requestCode);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    protected void startActivityFromRight(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    protected void finishActivityFromRight() {
        finish();
        overridePendingTransition(0, R.anim.base_slide_right_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.detachView();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (!this.getClass().getName().equals("com.wxx.ibeaconset.module.home.MainActivity"))
            overridePendingTransition(0, R.anim.base_slide_right_out);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            overridePendingTransition(0, R.anim.base_slide_right_out);
        }
        return super.onOptionsItemSelected(item);
    }

}
