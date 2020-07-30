package com.express.web.controller.backstage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author P1n93r
 */
@Controller
public class ShowBackstageViewController extends ManagerBaseController {
    /**
     * 访问后台管理首页
     * @param model 带管理员信息
     * @return 首页面
     */
    @RequestMapping("/index")
    public String showIndex(Model model){
        model.addAttribute("manager",manager);
        return "backstage/index";
    }


    /**
     * @return feedback显示界面
     */
    @RequestMapping("/feedback")
    public String showFeedback(){
        return "backstage/feedback";
    }

    /**
     * @return 管理员登录日志界面
     */
    @RequestMapping("/loginLog")
    public String showLoginLog(){
        return "backstage/loginLog";
    }


}
