package com.express.web.Interceptor;

import com.express.domain.Manager;
import com.express.domain.UserLogin;
import com.express.service.TokenService;
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
 * @date 2020/3/22 20:06
 */
public class ManagerLoginInterceptor implements HandlerInterceptor {
    @Autowired
    private TokenService tokenService;

    /*
     *
     *
     * */

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //1、从cookie中取User_TOKEN
        String token = (String) request.getSession().getAttribute("Manager_TOKEN");
        if (StringUtils.isBlank(token)) {
            //跳转到登录页面，带回调的url
            response.sendRedirect(request.getContextPath()+"/"
                    + "admin/page/login" + "?redirect=" +request.getScheme() + "://"+request.getServerName()+":"+request.getServerPort()+ request.getRequestURI());
            return false;
        } else {
            //根据token取用户信息
            Manager manager = tokenService.getManager_ByToken(token,request.getSession());
            //这里就是判断用户是否登录
            if (null == manager) {
                //跳转到登录页面，带回调的url
                response.sendRedirect(request.getContextPath()+"/"
                        + "admin/page/login"  + "?redirect=" + request.getRequestURI());
                return false;
            }
            //把用户信息放到request中
            request.setAttribute("Manager", manager);
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


}
