package com.express.service.user;



import com.express.common.TakeExpressResult;
import com.express.utils.Validator.LoginValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface LoginService {

    Map<String,String> faceLogin(HttpServletRequest request, HttpServletResponse response, String img);//人脸识别登录
    //账号登录
    TakeExpressResult Login(LoginValidator loginValidator, HttpServletRequest request, HttpServletResponse response);
    //手机短信验证登录
    TakeExpressResult PhoneLogin(String phone, String code, HttpServletRequest request, HttpServletResponse response, HttpSession session);
}
