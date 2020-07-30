package com.express.service;
import com.express.common.TakeExpressResult;
import com.express.domain.Manager;
import com.express.domain.UserLogin;

import javax.servlet.http.HttpSession;

/**
 *PictureServiceImpl  class
 * @author fyzn12
 * @date 2020/03/16
 */

public interface TokenService {

    //通过token获取用户,该方法主要是提供拦截器获取用户

    UserLogin getUser_ByToken(String token, HttpSession session) ;
    TakeExpressResult getUserByToken(String token, HttpSession session);
    Manager getManager_ByToken(String token, HttpSession session) ;
    TakeExpressResult getManagerByToken(String token, HttpSession session);

}
