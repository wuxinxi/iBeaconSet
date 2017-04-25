package com.wxx.ibeaconset.module.home;

import com.wxx.ibeaconset.base.BasePresenter;
import com.wxx.ibeaconset.bean.DeviceList;

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class MainPresenter extends BasePresenter<MainView> {

    private MainView view;

    private MainModel model = new MainCompl();

    public MainPresenter(MainView view) {
        this.view = view;
    }

    public void FetchToken() {
        if (model != null) {
            model.loadToken(new MainModel.OnTokenListener() {
                @Override
                public void onSuccess(String msg) {
                    if (view != null) {
                        view.onToken(msg);
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view != null) {
                        view.onFail(msg);
                    }
                }
            });
        }
    }


    public void FetchDeviceList(String token, int begin) {
        if (model != null) {
            model.loadDeviceList(new MainModel.OnDeviceListListener() {
                @Override
                public void onSuccess(List<DeviceList.DataBean.DevicesBean> devicesBeanList, int total_count) {
                    if (view != null) {
                        view.onDeviceList(devicesBeanList, total_count);
                    }
                }

                @Override
                public void onFail(String msg) {
                    if (view != null) {
                        view.onFail(msg);
                    }
                }
            }, token, begin);
        }
    }

}
