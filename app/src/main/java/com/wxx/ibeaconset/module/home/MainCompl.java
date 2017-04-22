package com.wxx.ibeaconset.module.home;

import com.google.gson.Gson;
import com.wxx.ibeaconset.MyApplication;
import com.wxx.ibeaconset.Utils.API;
import com.wxx.ibeaconset.bean.DeviceList;
import com.wxx.ibeaconset.httpclient.CallServer;
import com.wxx.ibeaconset.httpclient.HttpListener;
import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class MainCompl implements MainModel {
    @Override
    public void loadToken(final OnTokenListener listener) {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(API.URL_TOKEN, RequestMethod.GET);
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        CallServer.getHttpclient().add(0, request, new HttpListener<JSONObject>() {
            @Override
            public void success(int what, Response<JSONObject> response) {
                Logger.d(response.get().toString());
                JSONObject object = response.get();
                try {
                    String token = object.getString("access_token");
                    MyApplication.getInstance().setACCESSTION(token);
                    listener.onSuccess(token);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fail(int what, Response<JSONObject> response) {
                listener.onFail("网络或服务器异常！");
            }
        });

    }


    @Override
    public void loadDeviceList(final OnDeviceListListener listListener, final String token, int begin) {
        try {
            Request<JSONObject> request = NoHttp.createJsonObjectRequest(API.URL_SEARCH + token, RequestMethod.POST);
            JSONObject object = new JSONObject();

            object.put("type", 2);
            object.put("begin", begin);
            object.put("count", 20);

            request.setDefineRequestBodyForJson(object);
            request.setCacheMode(CacheMode.NONE_CACHE_REQUEST_NETWORK);
            CallServer.getHttpclient().add(1, request, new HttpListener<JSONObject>() {
                        @Override
                        public void success(int what, Response<JSONObject> response) throws JSONException {
                            Logger.d(response.get().toString());
                            JSONObject ob = response.get();
                            if (ob.getInt("errcode") == 0) {
                                DeviceList deviceList = new Gson().fromJson(response.get().toString(), DeviceList.class);
                                int total_count = deviceList.getData().getTotal_count();
                                listListener.onSuccess(deviceList.getData().getDevices(), total_count);
                            } else {
                                listListener.onFail(ob.toString());
                            }
                        }

                        @Override
                        public void fail(int what, Response<JSONObject> response) {
                            Logger.d("失敗");
                        }
                    }

            );
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
