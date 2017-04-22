package com.wxx.ibeaconset.httpclient;

import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONException;

public interface HttpListener<T> {

	void success(int what, Response<T> response) throws JSONException;

	void fail(int what, Response<T> response);
}
