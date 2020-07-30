package com.express.utils;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/** 调用阿里云的查询快递的api
 * @author P1n93r
 */
final public class ExpressUtils {
    private static String appCode;
    private static String host;
    private static String path;
    private static String method;
    static {
        String filePath = ExpressUtils.class.getClassLoader().getResource("api/express.properties").getPath();
        try (FileInputStream inputStream=new FileInputStream(filePath)){
            Properties properties = new Properties();
            properties.load(inputStream);
            appCode=properties.getProperty("api.code");
            host=properties.getProperty("api.host");
            path=properties.getProperty("api.path");
            method=properties.getProperty("api.method");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 调用阿里云快递查询api
     * @param no
     * @return
     */
    public static String query(String no){
        Map<String, String> headers = new HashMap<>(1);
        headers.put("Authorization", "APPCODE " + appCode);
        Map<String, String> querys = new HashMap<>(1);
        querys.put("no", no);
        try {
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
