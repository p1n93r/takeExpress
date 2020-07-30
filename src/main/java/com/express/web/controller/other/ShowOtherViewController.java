package com.express.web.controller.other;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ShowOtherViewController {

    @RequestMapping("/test")
    public String test(HttpServletRequest request){
        return "public/test";
    }

}
