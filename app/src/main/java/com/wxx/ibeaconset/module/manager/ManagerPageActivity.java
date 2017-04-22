package com.wxx.ibeaconset.module.manager;

import android.app.ProgressDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnNetWorkErrorListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.wxx.ibeaconset.MyApplication;
import com.wxx.ibeaconset.R;
import com.wxx.ibeaconset.adapter.PageAdapter;
import com.wxx.ibeaconset.base.BaseActivity;
import com.wxx.ibeaconset.bean.PageList;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class ManagerPageActivity extends BaseActivity<ManagerView, ManagerPresenter> implements ManagerView,
        OnRefreshListener, OnLoadMoreListener, OnNetWorkErrorListener {

    private static final String TAG = "MainActivity";
    @BindView(R.id.recylerView)
    LRecyclerView recylerView;

    private PageAdapter mAdapter;
    private LinearLayoutManager manager;
    private LRecyclerViewAdapter recyclerViewAdapter;
    private ProgressDialog mDialog;

    private int begin = 0;

    private int total_count;

    @Override
    protected ManagerPresenter createPresenter() {
        return new ManagerPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        manager = new LinearLayoutManager(getApplicationContext());
        recylerView.setLayoutManager(manager);
        recylerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mAdapter = new PageAdapter();
        recyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        recylerView.setOnRefreshListener(this);
        recylerView.setOnLoadMoreListener(this);
        recylerView.setOnNetWorkErrorListener(this);
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("正在加载请稍后……");
    }

    @Override
    protected void initData() {
        mPresenter.fetchPageList(MyApplication.getInstance().getACCESSTION(), begin);
    }

    @Override
    public void onFail(String msg) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageList(int type, List<PageList.DataBean.PagesBean> pagesBeanList, int total_count) {
        if (begin == 0) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mAdapter.add(pagesBeanList);
            recylerView.setAdapter(recyclerViewAdapter);
        } else {
            mAdapter.addMore(pagesBeanList);
        }
        begin += 15;
        this.total_count = total_count;
        recylerView.refreshComplete(15);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore() {
        if (mAdapter.getItemCount() < total_count)
            mPresenter.fetchPageList(MyApplication.getInstance().getACCESSTION(), begin);
        else recylerView.setNoMore(true);
    }

    @Override
    public void reload() {
        mPresenter.fetchPageList(MyApplication.getInstance().getACCESSTION(), begin);
    }

    @Override
    public void onRefresh() {
        begin = 0;
        mPresenter.fetchPageList(MyApplication.getInstance().getACCESSTION(), begin);
    }
}
