package com.wxx.ibeaconset.module.manager;

import android.app.ProgressDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import com.wxx.ibeaconset.inte.OnIteLongClick;
import com.wxx.ibeaconset.inte.OnItemclick;
import com.wxx.ibeaconset.module.update.UpdateActivity;
import com.yanzhenjie.nohttp.Logger;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class ManagerPageActivity extends BaseActivity<ManagerView, ManagerPresenter> implements ManagerView,
        OnRefreshListener, OnLoadMoreListener, OnNetWorkErrorListener, OnItemclick<PageList.DataBean.PagesBean>,
        OnIteLongClick<PageList.DataBean.PagesBean> {

    private static final String TAG = "MainActivity";
    @BindView(R.id.recylerView)
    LRecyclerView recylerView;
    @BindView(R.id.mtoolbar)
    Toolbar mtoolbar;

    private PageAdapter mAdapter;
    private LinearLayoutManager manager;
    private LRecyclerViewAdapter recyclerViewAdapter;
    private ProgressDialog mDialog;

    private int begin = 0;

    private int total_count;

    private static final int DEFAULT_CODE = 0;

    @Override
    protected ManagerPresenter createPresenter() {
        return new ManagerPresenter(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_manager;
    }

    @Override
    protected void initView() {
        mtoolbar.setTitle("页面管理");
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        manager = new LinearLayoutManager(getApplicationContext());
        recylerView.setLayoutManager(manager);
        recylerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        mAdapter = new PageAdapter();
        recyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        recylerView.setOnRefreshListener(this);
        recylerView.setOnLoadMoreListener(this);
        recylerView.setOnNetWorkErrorListener(this);
        mAdapter.setItemclick(this);
        mAdapter.setItemLongClick(this);
        mDialog = new ProgressDialog(this);
        mDialog.setMessage("正在加载请稍后……");
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.show();
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
    public void delResult(int postion, int code, String msg, List<PageList.DataBean.PagesBean> list) {
        if (code == 0) {
            Toast.makeText(this, "删除成功!", Toast.LENGTH_SHORT).show();
            mAdapter.notifyItemRemoved(postion);
            list.remove(postion);
        } else
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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

    @Override
    public void click(View view, int postion, List<PageList.DataBean.PagesBean> list) {
        PageList.DataBean.PagesBean bean = list.get(postion - 1);
        Intent intent = new Intent(ManagerPageActivity.this, UpdateActivity.class);
        intent.putExtra("pageList", bean);
        startActivityFromRight(intent, DEFAULT_CODE);
    }

    @Override
    public void longClick(View view, final int postion, final List<PageList.DataBean.PagesBean> list) {
        Logger.d(list.get(postion - 1).toString());
        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
        vibrator.vibrate(90);
        final AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("确定要删除吗？")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.fetchDel(list.get(postion - 1).getPage_id(), postion - 1, list);
                        dialog.dismiss();
                    }
                }).setNegativeButton("取消", null).show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Logger.e("requestCode=" + requestCode + ",resultCode=" + resultCode);
        if (resultCode == DEFAULT_CODE) {
            begin = 0;
            mPresenter.fetchPageList(MyApplication.getInstance().getACCESSTION(), begin);
        }
    }
}
