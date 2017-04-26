package com.wxx.ibeaconset.module.home;

import com.wxx.ibeaconset.bean.DeviceList;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public interface MainModel {

    void loadToken(OnTokenListener listener);

    interface OnTokenListener {
        void onSuccess(String msg);

        void onFail(String msg);
    }

    void loadDeviceList(OnDeviceListListener listListener, String token, int begin);

    interface OnDeviceListListener {
        void onSuccess(List<DeviceList.DataBean.DevicesBean> devicesBeanList, int total_count);

        void onFail(String msg);
    }

    void loadRelation(loadRelationListener listener, int device_id, int page_id);

    interface loadRelationListener {
        void onSuccess(String msg);

        void onFail(String msg);
    }

}
