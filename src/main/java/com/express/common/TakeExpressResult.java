package com.express.common;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

//自定义一个自定义响应结构，实现json和对象之间的转换，返回状态码
public class TakeExpressResult {
    //创建一个jackson对象，用于解析json文件
    private static final ObjectMapper jackson = new ObjectMapper();
    //定义响应业务状态码
    private Integer status;
    //响应消息
    private String msg;
    //定义响应数据
    private Object data;
    //创建多参构造函数
    public TakeExpressResult(Integer status, String msg, Object data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    //创建返回状态码正常的构造函数
    public TakeExpressResult(Object data){
        this.status = 200;//状态码为200代表返回值正常
        this.msg = "OK";//表示返回值信息
        this.data = data;
    }
    //创建只有Object的回调函数，返回TakeExpressResult对象
    public static TakeExpressResult ok(Object data) {
        return new TakeExpressResult(data);
    }
    public static TakeExpressResult ok() {
        return new TakeExpressResult(null);
    }
    //创建回调函数
    public static  TakeExpressResult build(Integer status, String msg, Object data){
        return new TakeExpressResult(status,msg,data);
    }
    //创建回调函数
    public static  TakeExpressResult build(Integer status, String msg){
        return new TakeExpressResult(status,msg,null);
    }
    //数值set和get方法
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
   //将json结果转化为TakeExpressResult对象,准确来说是转化为需要的pojo对象
    public static TakeExpressResult formatToPojo(String jsonData, Class<?> clzz){
        try {
            if (clzz == null){
                //返回当前TakeExpressResult的pojo对象
                return jackson.readValue(jsonData,TakeExpressResult.class);
            }
            JsonNode jsonNode = jackson.readTree(jsonData);//利用ObjectMapper对象读取数据，返回一个符合前端json读取的数据模式
            /*
            * 数组节点的指定元素的访问值的方法。对于其他节点，空总是返回。
              对于数组的节点，索引指定的确切位置在阵列，允许高效的迭代子元素
             （底层存储是保证高效可转位，即具有随机访问元素）。如果指数小于0，
              或大于或等于节点。size()，返回null；抛出任何指数也不例外。
            * */
            JsonNode data = jsonNode.get("data");
            Object obj = null;//定义未知对象
            //获取json最终转的pojo对象
            if (clzz != null){
                if (data.isObject()) {
                    obj = jackson.readValue(data.traverse(), clzz);
                } else if (data.isTextual()) {
                    obj = jackson.readValue(data.asText(), clzz);
                }
            }
            //利用TakeExpressResult的回调函数包装成TakeExpressResult对象并返回
            return build(jsonNode.get("status").intValue(),jsonNode.get("msg").asText(),obj);

        }catch (Exception e){
            return null ;
        }
    }
    //创建没有Object的转换
    public static TakeExpressResult format(String json){
        try{
            return jackson.readValue(json,TakeExpressResult.class);
        }catch (Exception e){
            return null;
        }
    }
    //将json转换为集合
    public static TakeExpressResult formatToList(String jsonData, Class<?> clazz){
        try{
            JsonNode jsonNode = jackson.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0) {
                obj = jackson.readValue(data.traverse(),
                        jackson.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        } catch (Exception e) {
            return null;
        }
    }

}
