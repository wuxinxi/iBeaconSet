package com.wxx.ibeaconset.module.home;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.InputFilter;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import com.wxx.ibeaconset.inte.OnIteLongClick;
import com.wxx.ibeaconset.inte.OnItemclick;
import com.wxx.ibeaconset.module.manager.ManagerPageActivity;
import com.wxx.ibeaconset.module.update.UpdateActivity;

import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView,
        OnRefreshListener, OnLoadMoreListener, OnNetWorkErrorListener,
        OnItemclick<DeviceList.DataBean.DevicesBean>, OnIteLongClick<DeviceList.DataBean.DevicesBean> {


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
        mAdapter.setItemLongClick(this);
        mDialog.setMessage("正在加载请稍后……");
        mDialog.setCanceledOnTouchOutside(false);
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
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
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
//        Intent intent = new Intent(MainActivity.this, RelationActivity.class);
//        intent.putExtra("device_id", devicesBeanList.get(postion - 1).getDevice_id());
//        startActivityFromRight(intent);
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
        } else if (item.getItemId() == R.id.add) {
            Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
            startActivityFromRight(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void longClick(View view, final int postion, final List<DeviceList.DataBean.DevicesBean> list) {
        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(90);
        final EditText editText = new EditText(this);
        editText.setHint("页面ID");
        editText.setInputType(InputType.TYPE_CLASS_NUMBER);
        editText.setMaxLines(1);
        editText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(6)});
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setView(editText)
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        mPresenter.FetchRelation(list.get(postion - 1).getDevice_id(), Integer.valueOf(editText.getText().toString().trim()));
                    }
                }).setNegativeButton("取消", null).show();
    }

    @Override
    public void onRelationS(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
