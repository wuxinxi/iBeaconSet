package com.wxx.ibeaconset;

import android.app.Application;

import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.OkHttpNetworkExecutor;

/**
 * 作者：Tangren_ on 2017/4/21 0021.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class MyApplication extends Application {

    private static MyApplication instance;

    private String ACCESSTION;

    private static final String TAG = "MyApplication";

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        NoHttp.initialize(this, new NoHttp.Config().setNetworkExecutor(new OkHttpNetworkExecutor()));
        Logger.setTag("T_T");
        Logger.setDebug(true);

    }

    public String getACCESSTION() {
        return ACCESSTION;
    }

    public void setACCESSTION(String ACCESSTION) {
        this.ACCESSTION = ACCESSTION;
    }

    public static MyApplication getInstance() {
        if (instance == null) {
            synchronized (MyApplication.class) {
                if (instance == null)
                    instance = new MyApplication();
            }
        }

        return instance;
    }


}
