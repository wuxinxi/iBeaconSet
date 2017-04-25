package com.wxx.ibeaconset.module.manager;

import com.google.gson.Gson;
import com.wxx.ibeaconset.MyApplication;
import com.wxx.ibeaconset.Utils.API;
import com.wxx.ibeaconset.bean.PageList;
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

import java.util.List;

/**
 * 作者：Tangren_ on 2017/4/22 0022.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class ManagerCompl implements ManagerModel {
    @Override
    public void loadPageList(final OnPageListListener listListener, String token, int begin) {
        try {
            final Request<JSONObject> request = NoHttp.createJsonObjectRequest(API.URL_SEARCHED + token, RequestMethod.POST);
            request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
            JSONObject object = new JSONObject();
            object.put("type", 2);
            Logger.d("begin:" + begin);
            object.put("begin", begin);
            object.put("count", 15);
            request.setDefineRequestBodyForJson(object);
            CallServer.getHttpclient().add(2, request, new HttpListener<JSONObject>() {
                @Override
                public void success(int what, Response<JSONObject> response) throws JSONException {
                    Logger.d(response.get().toString());
                    JSONObject ob = response.get();
                    if (ob.getInt("errcode") == 0) {
                        PageList pageList = new Gson().fromJson(response.get().toString(), PageList.class);
                        listListener.onSuccess(API.TYPE_1, pageList.getData().getPages(), pageList.getData().getTotal_count());
                    } else {
                        listListener.onFail(ob.toString());
                    }

                }

                @Override
                public void fail(int what, Response<JSONObject> response) {
                    listListener.onFail("网络或服务器异常！");
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadDel(final OnDelListener listener, int page_id, final int postion, final List<PageList.DataBean.PagesBean> list) {
        try {
            final Request<JSONObject> request = NoHttp.createJsonObjectRequest(API.URL_DELPAGE + MyApplication.getInstance().getACCESSTION(), RequestMethod.POST);
            request.setCacheMode(CacheMode.REQUEST_NETWORK_FAILED_READ_CACHE);
            JSONObject object = new JSONObject();
            object.put("page_id", page_id);
            request.setDefineRequestBodyForJson(object);
            CallServer.getHttpclient().add(3, request, new HttpListener<JSONObject>() {
                @Override
                public void success(int what, Response<JSONObject> response) throws JSONException {
                    Logger.d(response.get().toString());
                    JSONObject ob = response.get();
                    if (ob.getInt("errcode") == 0) {
                        listener.delResult(postion, 0, ob.toString(), list);
                    } else {
                        listener.delResult(postion, 1, ob.toString(), null);
                    }

                }

                @Override
                public void fail(int what, Response<JSONObject> response) {
                    listener.delResult(postion, 1, "网络或服务器异常！", null);
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
            listener.delResult(postion, 1, "异常：" + e.getMessage(), null);
        }
    }
}
