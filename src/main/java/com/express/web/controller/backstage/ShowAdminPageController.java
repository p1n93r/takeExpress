package com.express.web.controller.backstage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/21 11:59
 * 这里不能去掉，我是直接用admin替换掉ManagerBaseController的RequestMapping
 */
@Controller
@RequestMapping("/admin")
public class ShowAdminPageController extends ManagerBaseController {
    /*
    * 不能将登录的请求地址放在该控制器，这是因为继承了ManagerBaseController，@ModelAttribute注解的方法会优先被初始化
    * 登录访问地址放在这里会被拦截（已测试）
    * 当然也不能将backstage/index访问路径放在这里（没测试，理论上是不能放，不然拦截器和ManagerBaseController的方法冲突）
    * 下面所有方法在访问前都会先访问ManagerBaseController的方法，进行判断，若未登录，调用RedirectUtil的重新定向
    * 然后服务端终止，前端进行重定向到登录页面，当登录完成后再回调到backstage/index页面
    * */

    @RequestMapping("/modifyBaseInfo")
    public String modifyManagerBaseInfo(Model model){
        model.addAttribute("manager",manager);
        return "backstage/managermodifyInfo";
    }
    @RequestMapping("/managermodifySafe")
    public String modifyManagerSafe(Model model){
        model.addAttribute("manager",manager);
        return "backstage/managersafe";
    }
    @RequestMapping("/scantionUser")
    public String getApplyScantionUser(){
        return "backstage/scantionUser";
    }
    @RequestMapping("/scantionRegister")
    public String getRegister(){
        return "backstage/managerRegister";
    }
    @RequestMapping(value = "/applyScantionUser")
    public String getScantionUser(){
        return "backstage/applyScantionUser";
    }
}
