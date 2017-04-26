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

import org.json.JSONArray;
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
                    listener.onFail("异常：" + e.getMessage());
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
            request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
            JSONObject object = new JSONObject();

            object.put("type", 2);
            object.put("begin", begin);
            object.put("count", 15);

            request.setDefineRequestBodyForJson(object);
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
                            listListener.onFail("网络或服务器异常！");
                        }
                    }

            );
        } catch (JSONException e) {
            e.printStackTrace();
            listListener.onFail("异常：" + e.getMessage());
        }
    }

    @Override
    public void loadRelation(final loadRelationListener listener, int device_id, int page_id) {
        try {
            Request<JSONObject> request = NoHttp.createJsonObjectRequest(API.URL_BINDPAGE + MyApplication.getInstance().getACCESSTION(), RequestMethod.POST);
            request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);

            Logger.d("page_id:" + page_id);
            Logger.d("device_id:" + device_id);

            JSONObject object = new JSONObject();
            JSONObject ob = new JSONObject();
            ob.put("device_id", device_id);

            object.put("device_identifier", ob);

            JSONArray array = new JSONArray();
            array.put(page_id);

            object.put("page_ids", array);


            Logger.d(object.toString());
            request.setDefineRequestBodyForJson(object);
            CallServer.getHttpclient().add(5, request, new HttpListener<JSONObject>() {
                        @Override
                        public void success(int what, Response<JSONObject> response) throws JSONException {
                            Logger.d(response.get().toString());
                            JSONObject ob = response.get();
                            if (ob.getInt("errcode") == 0) {
                                listener.onSuccess("配置成功！");
                            } else {
                                listener.onFail(ob.toString());
                            }
                        }

                        @Override
                        public void fail(int what, Response<JSONObject> response) {
                            listener.onFail("网络或服务器异常！");
                        }
                    }

            );
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onFail("异常：" + e.getMessage());
        }
    }
}
