package com.express.web.controller.backstage;

import com.express.common.TakeExpressResult;
import com.express.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/22 19:04
 */
@Controller
public class CheckPhoneController {
    @Autowired
    private CheckService checkService;
    @RequestMapping(value = "/backstage/checkPhone",produces = "application/json;charset=utf-8")
    @ResponseBody
    public TakeExpressResult check(@RequestParam String phone){
        if (checkService.checkManagerPhone(phone.trim())){
            return TakeExpressResult.ok();
        }
        return TakeExpressResult.build(400,"手机号未注册");
    }
}
