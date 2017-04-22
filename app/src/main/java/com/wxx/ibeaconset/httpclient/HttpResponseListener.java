package com.wxx.ibeaconset.httpclient;

import com.yanzhenjie.nohttp.Logger;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;

public class HttpResponseListener<T> implements OnResponseListener<T> {

    private Request<T> request;

    private HttpListener<T> httpListener;

    /**
     * @param request  请求对象
     * @param callback 回调对象
     */
    public HttpResponseListener(final Request<T> request, HttpListener<T> callback) {
        this.httpListener = callback;
    }

    public void onFinish(int arg0) {
        Logger.d("完成");

    }

    public void onStart(int arg0) {
        Logger.i("开始");

    }

    // 成功回调
    public void onSucceed(int what, Response<T> response) {
        try {
            int responseCode = response.getHeaders().getResponseCode();
            System.out.println("responseCode:" + responseCode + "");
            if (responseCode > 400) {
                if (responseCode == 405) {
                    Logger.d("服务器暂不支持该类型！");
                }
            }

            if (httpListener != null) {

                httpListener.success(what, response);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFailed(int what, Response<T> response) {
        Logger.i("失败");
        if (httpListener != null)
            httpListener.fail(what, response);

    }


}
