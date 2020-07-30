package com.express.web.controller.visitor;
import com.express.common.TakeExpressResult;
import com.express.service.user.LoginService;
import com.express.service.manager.ManagerLoginService;
import com.express.utils.Validator.LoginValidator;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;
    //客服端请求为人脸识别登录

    @RequestMapping(value = "/facelogin.do",produces = "application/json;charset=utf-8")
    public TakeExpressResult faceLogin(@RequestParam String base ,HttpServletRequest request, HttpServletResponse response, Model model) {
        try{
            if (StringUtils.isBlank(base)){
                return TakeExpressResult.build(400,"没检测到人脸");
            }
            Map<String,String> map = loginService.faceLogin(request,response,base);
            if (map.get("status").equals("0")){
                return TakeExpressResult.ok();
            }
            return TakeExpressResult.build(400,map.get("msg"));
        }catch (Exception e){
            System.out.println("异常"+e.getMessage());
            return TakeExpressResult.build(400,"人脸识别异常");
        }
    }
    @RequestMapping(value = "/phoneLogin.do",produces = "application/json;charset=utf-8")
    public TakeExpressResult doYunPianLogin(@RequestParam("phone") String phone,@RequestParam("code") String code, HttpServletRequest request, HttpServletResponse response){
        try {
            if (StringUtils.isBlank(phone)||StringUtils.isBlank(code)){
                return TakeExpressResult.build(400,"数据不能为空");
            }
            HttpSession session = request.getSession();
            TakeExpressResult takeExpressResult = loginService.PhoneLogin(phone,code,request,response,session);
            return takeExpressResult;
        }catch (Exception e){
            return TakeExpressResult.build(400,"登录异常");
        }
    }
    @RequestMapping(value = "/idLogin.do",produces = "application/json;charset=utf-8")
    public TakeExpressResult getLogin(@Valid LoginValidator loginValidator, HttpServletRequest request, HttpServletResponse response){
        TakeExpressResult takeExpressResult = loginService.Login(loginValidator,request,response);
        return takeExpressResult;
    }

}
