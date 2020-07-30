package com.express.web.controller.visitor;
import com.express.common.TakeExpressResult;
import com.express.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
@Controller
public class CheckController {
    @Autowired
    private CheckService checkService;
    @RequestMapping(value = "/checkRegisterphone",produces = "application/json;charset=utf-8" )
    @ResponseBody
    public TakeExpressResult checkPhone(String phone , HttpServletRequest request){
        /*
        * 注册验证*/
        try {
            //checkService返回false代表不存在，返回true代表存在
            if (!checkService.checkPhone(phone.trim())){
                //返回200，代表
                return TakeExpressResult.ok();
            }else {
                return TakeExpressResult.build(400,"号码已注册");
            }
        }catch (Exception e){
            return TakeExpressResult.build(400,"查询电话异常");
        }
    }
    @RequestMapping(value = "/checkloginid.do",produces = "application/json;charset=utf-8")
    @ResponseBody
    public TakeExpressResult checkLoginId(@RequestBody String loginid){
        try {
            if (checkService.checkLoginId(loginid)){
                return TakeExpressResult.ok();
            }else {
                return TakeExpressResult.build(400,"账号不存在");
            }
        }catch (Exception e){
            return TakeExpressResult.build(400,"登录异常");
        }
    }
    @RequestMapping(value = "/checkUserPhoneLogin",produces = "application/json;charset=utf-8")
    @ResponseBody
    public TakeExpressResult checkUserPhoneLogin(@RequestParam String phone){
        if (checkService.checkUserPhone(phone.trim())){
            return TakeExpressResult.ok();
        }
        return TakeExpressResult.build(400,"手机号未注册");
    }
    @RequestMapping(value = "/checkemail",produces = "application/json;charset=utf-8")
    @ResponseBody
    public TakeExpressResult checkEmail(@RequestParam String email){
        if (checkService.checkEmail(email)){
            return TakeExpressResult.ok();
        }
        return TakeExpressResult.build(400,"邮箱已被注册");
    }
}
