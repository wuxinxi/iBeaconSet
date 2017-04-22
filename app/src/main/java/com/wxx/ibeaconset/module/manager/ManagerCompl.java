package com.wxx.ibeaconset.module.manager;

import com.google.gson.Gson;
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
            request.setCacheMode(CacheMode.NONE_CACHE_REQUEST_NETWORK);
            JSONObject object = new JSONObject();
            object.put("type", 2);
            object.put("begin", begin);
            object.put("count", 20);
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
}
