package com.express.service.manager;

import com.express.common.TakeExpressResult;
import com.express.utils.Validator.LoginValidator;
import com.express.utils.Validator.PhoneloginValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/22 17:44
 */
public interface ManagerLoginService {
    Map<String,String> faceLogin(HttpServletRequest request, HttpServletResponse response, String img);//人脸识别登录
    //账号登录
    TakeExpressResult Login(LoginValidator loginValidator, HttpServletRequest request, HttpServletResponse response);
    //手机短信验证登录
    TakeExpressResult PhoneLogin(PhoneloginValidator phoneloginValidator, HttpServletRequest request, HttpServletResponse response, HttpSession session);
}
