package com.wxx.ibeaconset.module.home;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
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
import com.wxx.ibeaconset.adapter.DeviceAdapter;
import com.wxx.ibeaconset.base.BaseActivity;
import com.wxx.ibeaconset.bean.DeviceList;
import com.wxx.ibeaconset.inte.OnItemclick;
import com.wxx.ibeaconset.module.manager.ManagerPageActivity;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView,
        OnRefreshListener, OnLoadMoreListener, OnNetWorkErrorListener, OnItemclick<DeviceList.DataBean.DevicesBean> {


    private static final String TAG = "MainActivity";
    @BindView(R.id.recylerView)
    LRecyclerView recylerView;

    private DeviceAdapter mAdapter;
    private LinearLayoutManager manager;
    private LRecyclerViewAdapter recyclerViewAdapter;
    private ProgressDialog mDialog;

    private int begin = 0;

    private int total_count;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
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
        mAdapter = new DeviceAdapter();
        recyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        recylerView.setOnRefreshListener(this);
        recylerView.setOnLoadMoreListener(this);
        recylerView.setOnNetWorkErrorListener(this);
        mDialog = new ProgressDialog(this);
        mAdapter.setItemclick(this);
        mDialog.setMessage("正在加载请稍后……");

    }

    @Override
    protected void initData() {
        mDialog.show();
        mPresenter.FetchToken();
    }


    @Override
    public void onToken(String token) {
        mPresenter.FetchDeviceList(token, begin);
    }

    @Override
    public void onFail(String msg) {
        if (mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

    @Override
    public void onDeviceList(List<DeviceList.DataBean.DevicesBean> devicesBeanList, int total_count) {
        if (begin == 0) {
            if (mDialog != null && mDialog.isShowing()) {
                mDialog.dismiss();
            }
            mAdapter.add(devicesBeanList);
            recylerView.setAdapter(recyclerViewAdapter);
        } else {
            mAdapter.addMore(devicesBeanList);
        }
        begin += 15;
        this.total_count = total_count;
        recylerView.refreshComplete(15);
        recyclerViewAdapter.notifyDataSetChanged();
    }

    //加载更多
    @Override
    public void onLoadMore() {
        if (mAdapter.getItemCount() < total_count)
            mPresenter.FetchDeviceList(MyApplication.getInstance().getACCESSTION(), begin);
        else recylerView.setNoMore(true);
    }


    //下拉刷新
    @Override
    public void onRefresh() {
        begin = 0;
        mPresenter.FetchDeviceList(MyApplication.getInstance().getACCESSTION(), begin);
    }

    //加载失败重新请求
    @Override
    public void reload() {
        mPresenter.FetchDeviceList(MyApplication.getInstance().getACCESSTION(), begin);
    }

    @Override
    public void click(View view, int postion, List<DeviceList.DataBean.DevicesBean> devicesBeanList) {

        Toast.makeText(this, devicesBeanList.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.manager) {
            Intent intent = new Intent(MainActivity.this, ManagerPageActivity.class);
            startActivityFromRight(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
