package com.express.web.controller.backstage;
import com.express.common.TakeExpressResult;
import com.express.service.manager.ManagerLoginService;
import com.express.utils.Validator.LoginValidator;
import com.express.utils.Validator.PhoneloginValidator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/22 17:18
 */
@Controller
@RequestMapping("/admin")
public class ManagerLoginController {
    @Autowired
    private ManagerLoginService managerLoginService;


    @RequestMapping(value = "/login",produces = "application/json;charset=utf-8")
    @ResponseBody
    public TakeExpressResult managerLogin(@Valid LoginValidator loginValidator, HttpServletRequest request, HttpServletResponse response) {
        return managerLoginService.Login(loginValidator,request,response);
    }


    @RequestMapping(value = "/facelogin",produces = "application/json;charset=utf-8")
    @ResponseBody
    public TakeExpressResult managerFaceLogin(@RequestParam String img, HttpServletRequest request, HttpServletResponse response) {
        try{
            if (StringUtils.isBlank(img)){
                return TakeExpressResult.build(400,"没检测到人脸");
            }
            Map<String,String> map = managerLoginService.faceLogin(request,response,img);
           if (map.get("status").equals("0")){
               return TakeExpressResult.ok();
           }
           return TakeExpressResult.build(400,"请绑定人脸识别后再进行人脸识别登录");
        }catch (Exception e){
            System.out.println("异常"+e.getMessage());
            return TakeExpressResult.build(400,"人脸识别异常");
        }
    }


    @RequestMapping(value = "/phonelogin",produces = "application/json;charset=utf-8")
    @ResponseBody
    public TakeExpressResult managerPhoneLogin(@Valid PhoneloginValidator phoneloginValidator, HttpServletRequest request, HttpServletResponse response) {
        return managerLoginService.PhoneLogin(phoneloginValidator,request,response,request.getSession());
    }
    //注销登录

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        //注销方法
        //通过session.invalidata()方法来注销当前的session
        request.getSession().invalidate();
        return "backstage/login";
    }


    @RequestMapping(value = "/page/login")
    public String modifyManagerlogin(String redirect, Model model){
        model.addAttribute("redirect",redirect);
        return "backstage/login";
    }
}
