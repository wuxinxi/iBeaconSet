package com.wxx.ibeaconset.Utils;

/**
 * 作者：Tangren_ on 2017/4/21 0021.
 * 邮箱：wu_tangren@163.com
 * TODO:用一句话概括
 */


public class API {

    //查询已有列表
    public static final int TYPE_1 = 1;

    //删除列表
    public static final int TYPE_2 = 2;

    private static final String URL = "https://api.weixin.qq.com/shakearound/";

    private static final String access_token = "access_token=";
    public static final String appid = "wx812954dc9708e485";
    public static final String secret = "5edd03d2a2adff1c5ec1e9191275e1c0";

    //获取Token
    public static final String URL_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appid + "&secret=" + secret + "";

    //查询列表
    public static final String URL_SEARCH = URL + "device/search?" + access_token;

    //申请设备
    public static final String URL_APPLYID = URL + "device/applyid?" + access_token;

    //审核状态
    public static final String URL_APPLYSTATUS = URL + "applystatus?" + access_token;

    //新增页面
    public static final String URL_ADDPAGE = "https://api.weixin.qq.com/shakearound/page/add?" + access_token;

    //编辑页面信息
    public static final String URL_UPDATE = "https://api.weixin.qq.com/shakearound/page/update?" + access_token;

    //查询已有的页面列表
    public static final String URL_SEARCHED = "https://api.weixin.qq.com/shakearound/page/search?" + access_token;

    //删除页面
    public static final String URL_DELPAGE = "https://api.weixin.qq.com/shakearound/page/delete?" + access_token;

    //新增素材（摇一摇icon）
    public static final String URL_ADDICON = "https://api.weixin.qq.com/shakearound/material/add?" + access_token;

    //配置关联.绑定设备与页面
    public static final String URL_BINDPAGE = "https://api.weixin.qq.com/shakearound/device/bindpage?" + access_token;

}
