package com.wxx.ibeaconset.module.relation;

import com.wxx.ibeaconset.Utils.API;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.Request;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：Tangren_ on 2017/4/25 0025.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class RelationCompl implements RelationModel {
    @Override
    public void FetchRelation(loadRelation listener, int page_ids, int device_id) {
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(API.URL_BINDPAGE, RequestMethod.POST);
        JSONObject object = new JSONObject();
        JSONObject ob = new JSONObject();
        try {
            ob.put("device_id", device_id);
            object.put("device_identifier", ob);
            object.put("page_ids", "[" + page_ids + "]");

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
