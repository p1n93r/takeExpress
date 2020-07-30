package com.express.web.controller.visitor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ShowVisitorViewController {
    @RequestMapping("/login")
    public String showDemoString (String redirect, Model model){
        model.addAttribute("redirect",redirect);
        System.out.println("redirect  ;"+redirect);
        return "visitor/login";
    }
    //注销登录

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request){
        //注销方法
        //通过session.invalidata()方法来注销当前的session
        request.getSession().invalidate();
        return "visitor/login";

    }

    @RequestMapping("/registerView")
    public String showRegister(){
        return "visitor/register";
    }
}
