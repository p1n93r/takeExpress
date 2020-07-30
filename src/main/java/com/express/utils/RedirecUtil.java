package com.express.utils;
import lombok.extern.slf4j.Slf4j;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/4/18 13:46
 * 主要作用于ajax请求，后台重定向
 *
 * 提供日志接口
 * 提供获取具体日志对象的方法
 */
@Slf4j
public class RedirecUtil {
    public  static void  redirect(HttpServletRequest request,HttpServletResponse response,String redirectUrl){
        try {
            /*
            * 如果是ajax请求
            * */
            String ajax = "XMLHttpRequest";
            String header = "X-Requested-With";
            if(ajax.equals(request.getHeader(header))){
                sendRedirect(response,redirectUrl);
            } //如果是浏览器地址栏请求
            else {
                log.debug("normal redirect ");
               response.sendRedirect(redirectUrl);
            }
        }catch (Exception e){

        }
    }
    /**
     *功能描述

     * @Description   Ajax请求时重定向处理
     * @param:
     * @return:
     *
     */
    private static void sendRedirect(HttpServletResponse response, String redirectUrl){
        try {
            //这里并不是设置跳转页面，而是将重定向的地址发给前端，让前端执行重定向
            //告诉ajax我是重定向
            response.setHeader("REDIRECT", "redirect");
            //设置跳转地址
            response.setHeader("redirectUrl", redirectUrl);
            //设置跳转使能 该参数不能少，不然传回不了前端，在服务端就被拦截报403错误而终止
            response.setHeader("enableRedirect","true");
            //设置拦截，服务端拦截，交给前端进行重定向
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.flushBuffer();
        } catch (IOException ex) {
            log.error("Could not redirect to: " + redirectUrl, ex);
        }
    }


}
