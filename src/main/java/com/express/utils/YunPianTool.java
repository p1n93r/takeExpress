package com.express.utils;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

import java.util.HashMap;
import java.util.Map;

public class YunPianTool {
    public Map<String,Object>  Main(String phone,String text){
        //云片上面申请的子系统
        YunpianClient clnt = new YunpianClient("9e30b73d1cd5882ca800e2d8c3d0e158").init();
        //发送短信API
        Map<String, String> param = clnt.newParam(2);
        param.put(YunpianClient.MOBILE, phone);
        param.put(YunpianClient.TEXT, text);
        Result<SmsSingleSend> r = clnt.sms().single_send(param);
        Integer status = r.getCode();
        clnt.close();
        Map<String, Object> map = new HashMap<>();
        map.put("status",status);
        map.put("msg",r.getDetail());
        return map;
    }
}