package com.wxx.ibeaconset;

import android.app.Application;
import android.content.pm.PackageManager;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
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


        String versionName;
        try {
            versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            versionName = "2.0.1";
        }
        SophixManager.getInstance().setContext(this)
                .setAppVersion(versionName)
                .setAesKey(null)
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                            Logger.d("补丁加载成功");
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
                            Logger.d("新补丁生效需要重启. 开发者可提示用户或者强制重启");
                        } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                            // SophixManager.getInstance().cleanPatches();
                            Logger.d("内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载");
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                            Logger.d("其它错误信息,");
                        }
                    }
                }).initialize();
        SophixManager.getInstance().queryAndLoadNewPatch();

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
