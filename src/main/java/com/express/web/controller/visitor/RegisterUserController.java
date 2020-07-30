package com.express.web.controller.visitor;

import com.express.common.TakeExpressResult;
import com.express.service.user.RegisterService;
import com.express.utils.Validator.RegisterValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletRequest;

import javax.validation.Valid;

@Controller
public class RegisterUserController {
    @Autowired
    private RegisterService registerService;
    @RequestMapping(value = "/register.do",produces = "application/json;charset=utf-8")
    @ResponseBody
    public TakeExpressResult register(@Valid RegisterValidator registerValidator, HttpServletRequest request){

        //两种转换
        /*
        * RequestBody是将json转化为一个object对象
        * RequestParam是接收key-value键值对*/

        TakeExpressResult takeExpressResult = registerService.register(registerValidator, request);
        return takeExpressResult;
    }

}
