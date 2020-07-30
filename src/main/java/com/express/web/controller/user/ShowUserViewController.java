package com.express.web.controller.user;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author P1n93r
 * 用于显示User模块的视图的控制器
 */
@Controller
@RequestMapping("/user")
public class ShowUserViewController extends UserBaseController{
    @RequestMapping("/index")
    public String showUserIndex(Model model){
        model.addAttribute("user",user);
        return "user/index";
    }

}
