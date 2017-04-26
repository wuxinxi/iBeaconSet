package com.wxx.ibeaconset.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.wxx.ibeaconset.R;
import com.wxx.ibeaconset.Utils.Util;
import com.wxx.ibeaconset.bean.DeviceList;
import com.wxx.ibeaconset.inte.OnIteLongClick;
import com.wxx.ibeaconset.inte.OnItemclick;
import com.wxx.ibeaconset.viewholder.DeviceHolder;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class DeviceAdapter extends RecyclerView.Adapter<DeviceHolder> {

    private OnItemclick itemclick;
    private OnIteLongClick onIteLongClick;
    private List<DeviceList.DataBean.DevicesBean> devicesBeanList;
    private LayoutInflater mInflater;

    @Override
    public DeviceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mInflater = LayoutInflater.from(parent.getContext());
        return new DeviceHolder(mInflater.inflate(R.layout.item_device_list, parent, false), itemclick, onIteLongClick, devicesBeanList);
    }

    @Override
    public void onBindViewHolder(DeviceHolder holder, int position) {

        DeviceList.DataBean.DevicesBean bean = devicesBeanList.get(position);
        if (bean == null)
            return;
        holder.comment.setText("备注：" + bean.getComment());
        holder.device_id.setText("设备ID:" + bean.getDevice_id());
        holder.major.setText("Maojor：" + bean.getMajor());
        holder.minor.setText("Mainor:" + bean.getMinor());
        holder.status.setText("激活状态：" + Util.getStatus(bean.getStatus()));
        holder.last_active_time.setText("最近被摇到日期:" + Util.long2Date(bean.getLast_active_time()));
        holder.uuid.setText("UUID：" + bean.getUuid());
    }

    @Override
    public int getItemCount() {
        return devicesBeanList == null ? 0 : devicesBeanList.size();
    }

    public void setItemclick(OnItemclick itemclick) {
        this.itemclick = itemclick;
    }

    public void setItemLongClick(OnIteLongClick iteLongClick) {
        this.onIteLongClick = iteLongClick;
    }

    public void add(List<DeviceList.DataBean.DevicesBean> devicesBeanList) {
        this.devicesBeanList = devicesBeanList;
        notifyDataSetChanged();
    }

    public void addMore(List<DeviceList.DataBean.DevicesBean> devicesBeanList) {
        if (devicesBeanList == null) {
            add(devicesBeanList);
        } else {
            this.devicesBeanList.addAll(devicesBeanList);
            notifyDataSetChanged();
        }
    }


}
