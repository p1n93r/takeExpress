package com.express.utils;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Cassie
 */
final public class IpUtils {
    private static String ak;
    private static String host;
    private static String path;
    private static String method;
    private static String coor;
    static{
        String filePath = IpUtils.class.getClassLoader().getResource("api/ipAddress.properties").getPath();
        try(FileInputStream inputStream= new FileInputStream(filePath)){
            Properties properties = new Properties();
            properties.load(inputStream);
            ak=properties.getProperty("api.ak");
            host=properties.getProperty("api.host");
            path=properties.getProperty("api.path");
            method=properties.getProperty("api.method");
            coor=properties.getProperty("api.coor");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static String queryIpAddress(String ip){
        Map<String, String> headers = new HashMap<>(1);
        headers.put("X-Forwarded-For",ip);
        HashMap<String, String> querys = new HashMap<>();
        querys.put("ip",ip);
        querys.put("ak",ak);
        querys.put("coor",coor);
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return null;
    }



}
