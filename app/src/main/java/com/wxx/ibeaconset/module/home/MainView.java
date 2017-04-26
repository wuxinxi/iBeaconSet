package com.wxx.ibeaconset.module.home;

import com.wxx.ibeaconset.bean.DeviceList;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public interface MainView {
    void onToken(String token);

    void onFail(String msg);

    void onDeviceList(List<DeviceList.DataBean.DevicesBean> devicesBeanList, int total_count);

    void onRelationS(String msg);

}
