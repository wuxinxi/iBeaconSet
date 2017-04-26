package com.wxx.ibeaconset.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.wxx.ibeaconset.R;
import com.wxx.ibeaconset.bean.DeviceList;
import com.wxx.ibeaconset.inte.OnIteLongClick;
import com.wxx.ibeaconset.inte.OnItemclick;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class DeviceHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private OnItemclick itemclick;
    private OnIteLongClick onIteLongClick;

    private List<DeviceList.DataBean.DevicesBean> devicesBeanList;

    public TextView comment;
    public TextView device_id;
    public TextView major;
    public TextView minor;
    public TextView status;
    public TextView last_active_time;
    public TextView uuid;

    public DeviceHolder(View itemView, OnItemclick itemclick, OnIteLongClick onIteLongClick, List<DeviceList.DataBean.DevicesBean> devicesBeanList) {
        super(itemView);
        comment = (TextView) itemView.findViewById(R.id.comment);
        device_id = (TextView) itemView.findViewById(R.id.device_id);
        major = (TextView) itemView.findViewById(R.id.major);
        minor = (TextView) itemView.findViewById(R.id.minor);
        status = (TextView) itemView.findViewById(R.id.status);
        last_active_time = (TextView) itemView.findViewById(R.id.last_active_time);
        uuid = (TextView) itemView.findViewById(R.id.uuid);
        this.itemclick = itemclick;
        this.onIteLongClick = onIteLongClick;
        this.devicesBeanList = devicesBeanList;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (itemclick != null) {
            itemclick.click(itemView, getAdapterPosition(), devicesBeanList);
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (onIteLongClick != null)
            onIteLongClick.longClick(v, getAdapterPosition() , devicesBeanList);
        return true;
    }
}
