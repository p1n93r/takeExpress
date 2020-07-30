package com.express.test;

import com.express.domain.Feedback;
import com.express.domain.User;
import com.express.utils.IpUtils;
import com.express.utils.alipay.PayOnPc;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;

import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

/**
 * @author Cassie
 */
public class BasicTest {

    /**
     * 测试获取当前时间，精确到时分秒
     */
    @Test
    public void testGetTime(){
        int time = (int) LocalDateTime.now().toEpochSecond(ZoneOffset.of("+8"));
        System.out.println("当前时间======================"+time);
        System.out.println("这才是当前时间="+LocalDateTime.now());
    }


    @Test
    public void testJson(){
        JSONObject json = new JSONObject();
        json.put("love","笨笨");
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(new JSONObject());
        jsonArray.add(new JSONObject());
        json.put("data",jsonArray);

        System.out.println(json.toString());

    }

    @Test
    public void testIpAddress(){
        System.out.println(IpUtils.queryIpAddress("223.104.197.43"));
    }


    @Test
    public void testDomain() throws Exception {
        JSONObject res = PayOnPc.query("781e2514-bc3f-49f3-9408-89c0eed8d826");
        System.out.println("结果："+res.toString());

    }



}
