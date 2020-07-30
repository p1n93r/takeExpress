package com.express.service.impl;

import com.express.common.TakeExpressResult;

import com.express.domain.Manager;
import com.express.domain.UserLogin;
import com.express.service.TokenService;
import com.express.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.stereotype.Service;


import javax.servlet.http.HttpSession;

/**
 *PictureServiceImpl  class
 * @author fyzn12
 * @date 2020/03/16
 */
@Service
public class TokenServiceImpl implements TokenService {
    @Override
    public UserLogin getUser_ByToken(String token,HttpSession session) {
        try {
            String json = (String) session.getAttribute(token);
            UserLogin userLogin = JsonUtils.jsonToPojo(json,UserLogin.class);
            if (userLogin!=null) {
                return userLogin;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
    @Override
    public TakeExpressResult getUserByToken(String token, HttpSession session) {
        String json = (String) session.getAttribute(token);
        //没有取到用户信息
        if (StringUtils.isBlank(json)) {
            return TakeExpressResult.build(400, "Session已经过期");
        }
        //取到用户信息
        //把json数据转换成java对象
        UserLogin user = JsonUtils.jsonToPojo(json, UserLogin.class);
        //更新session的过期时间
        session.setMaxInactiveInterval(1800);
        return TakeExpressResult.ok(user);
    }

    @Override
    public Manager getManager_ByToken(String token, HttpSession session) {
        try {
            String json = (String) session.getAttribute(token);
            Manager manager = JsonUtils.jsonToPojo(json,Manager.class);
            if (manager!=null) {
                return manager;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    @Override
    public TakeExpressResult getManagerByToken(String token, HttpSession session) {
        String json = (String) session.getAttribute(token);
        //没有取到用户信息
        if (StringUtils.isBlank(json)) {
            return TakeExpressResult.build(400, "Session已经过期");
        }
        //取到用户信息
        //把json数据转换成java对象
        Manager manager = JsonUtils.jsonToPojo(json, Manager.class);
        //更新session的过期时间
        session.setMaxInactiveInterval(1800);
        return TakeExpressResult.ok(manager);
    }

}
