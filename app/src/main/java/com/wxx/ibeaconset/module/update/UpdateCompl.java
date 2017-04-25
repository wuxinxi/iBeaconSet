package com.wxx.ibeaconset.module.update;

import com.wxx.ibeaconset.MyApplication;
import com.wxx.ibeaconset.Utils.API;
import com.wxx.ibeaconset.Utils.Util;
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

import java.io.InputStream;

/**
 * 作者：Tangren_ on 2017/4/25 0025.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class UpdateCompl implements UpdateModel {

    @Override
    public void fetchUpdate(loadUpdate listener, PageList.DataBean.PagesBean pagesBean) {
        try {
            Request<JSONObject> request = NoHttp.createJsonObjectRequest(API.URL_UPDATE + MyApplication.getInstance().getACCESSTION(), RequestMethod.POST);
            request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
            JSONObject object = new JSONObject();

            object.put("page_id", pagesBean.getPage_id());
            object.put("title", pagesBean.getTitle());
            object.put("description", pagesBean.getDescription());
            object.put("page_url", pagesBean.getPage_url());
            object.put("comment", pagesBean.getComment());
            object.put("icon_url", pagesBean.getIcon_url());

            CallServer.getHttpclient().add(5, request, new LoadUpdateListener(listener));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addImage(loadUpdate listener, InputStream stream) {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(API.URL_ADDICON + MyApplication.getInstance().getACCESSTION(), RequestMethod.POST);
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        request.add("media", Util.getCurrentTime() + "jpg");
        request.setDefineRequestBody(stream, "application/octet-stream");
        CallServer.getHttpclient().add(4, request, new LoadUpdateListener(listener));
    }

    private class LoadUpdateListener implements HttpListener<JSONObject> {

        private loadUpdate listener;

        public LoadUpdateListener(loadUpdate listener) {
            this.listener = listener;
        }

        @Override
        public void success(int what, Response<JSONObject> response) throws JSONException {
            JSONObject object = response.get();
            Logger.d(object.toString());
            int errcode = object.getInt("errcode");
            if (errcode == 0) {
                if (what == 4) {
                    JSONObject data = object.getJSONObject("data");
                    String pic_url = data.getString("pic_url");
                    listener.onSuccess(1, 4, pic_url);
                } else if (what == 5) {
                    listener.onSuccess(1, 5, "修改成功!");
                } else {
                    listener.onFail(object.toString());
                }
            } else {
                listener.onFail(object.toString());
            }
        }

        @Override
        public void fail(int what, Response<JSONObject> response) {
            listener.onFail("网络或服务器异常！");
        }
    }
}
