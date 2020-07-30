package com.express.web.Interceptor;

import com.express.domain.UserLogin;
import com.express.service.TokenService;
import com.express.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/16 15:52
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    /*
    * 后期需要修改 http://localhost:8080/tekeExpress_war_exploded/
    * 登录地址 login
    * */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //1、从cookie中取User_TOKEN
        String token = (String) request.getSession().getAttribute("User_TOKEN");
        if (StringUtils.isBlank(token)) {
            //跳转到登录页面，带回调的url
            response.sendRedirect(request.getContextPath()+"/"
                    + "login" + "?redirect=" +request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+ request.getRequestURI());
            return false;
        } else {
            //根据token取用户信息
            UserLogin user = tokenService.getUser_ByToken(token,request.getSession());
            //这里就是判断用户是否登录
            if (null == user) {
                //跳转到登录页面，带回调的url
                response.sendRedirect(request.getContextPath()+"/"
                        + "login"  + "?redirect=" + getUrl(request));
                return false;
            }
            //把用户信息放到request中
            request.setAttribute("user", user);
        }
        //放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception , ModelAndViewDefiningException {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
    private String getUrl(HttpServletRequest request) {
        String url = request.getScheme() + "://"
                + request.getServerName() + ":"
                + request.getServerPort()
                + request.getContextPath()
                + request.getRequestURI();
        String url2 = request.getRequestURL().toString();
        return url;
    }

}
