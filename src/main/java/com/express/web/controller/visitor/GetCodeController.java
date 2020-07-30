package com.express.web.controller.visitor;


import com.express.common.TakeExpressResult;
import com.express.utils.YunPianTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

@Controller
public class GetCodeController {
    //接收发送验证码的请求
    @RequestMapping(value = "/getcode.do",produces = "application/json;charset=utf-8")
    @ResponseBody
    public TakeExpressResult getcode(@RequestParam("phone") String phone, HttpServletRequest request){
        try{
            if (phone.isEmpty()){
                return TakeExpressResult.build(400,"手机号不能为空");
            }
            int ran=(int)(Math.random()*9000)+1000;
            String text="【张荣军】您的验证码是"+ran+"。如非本人操作，请忽略本短信";
            Map<String,Object> map = new YunPianTool().Main(phone.trim(),text);
            if ((Integer) map.get("status")!=0){
                return TakeExpressResult.build((Integer) map.get("status"),(String) map.get("msg"));
            }
            HttpSession session = request.getSession();
            //以电话为建，验证码为值
            session.setAttribute(phone.trim(),ran);
            session.setAttribute(phone.trim()+"Time",new Date());
            session.setMaxInactiveInterval(60);
            return TakeExpressResult.ok("发送验证码成功");
        }catch (Exception e){
            return TakeExpressResult.build(400,"短信发送失败");
        }
    }
}
