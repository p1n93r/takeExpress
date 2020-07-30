package com.express.web.controller.backstage;
import com.express.common.DataTableResult;
import com.express.common.TakeExpressResult;
import com.express.service.managerUser.ManageUserService;
import com.express.service.managerUser.ManagerRegisterService;
import com.express.utils.Validator.UserJurisdictionApplicationValidator;
import com.express.web.controller.backstage.ManagerBaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/18 19:17
 */
@RestController
public class AdminUserController extends ManagerBaseController {
    @Autowired
    private ManageUserService manageUserService;
    @Autowired
    private ManagerRegisterService managerRegisterService;

    /*
     * sanctiosUser 用户权限限制
    */

    @RequestMapping(value = "/sanctiosUser",produces = "application/json;charset=utf-8")
    public TakeExpressResult sanctiosUserAction(String userid, String status,String content,HttpServletRequest request){
        try {
            return manageUserService.sanctiosUser(Integer.parseInt(userid),status,content,request);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return TakeExpressResult.build(400,"限制用户权限异常");
        }
    }

    /*
     * auditUserJurisdictionApplicaton 审核用户权限申请
     * */

    @RequestMapping(value = "/auditApplicaton",produces = "application/json;charset=utf-8")

    public TakeExpressResult auditUserJurisdictionApplicaton(String userid, String status,String content,Boolean check,HttpServletRequest request){
        try {
            return manageUserService.auditUserJurisdictionApplicaton(Integer.parseInt(userid),status,content,check,request);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return TakeExpressResult.build(400,"限制用户权限异常");
        }
    }

    @RequestMapping(value = "/auditRegister",produces = "application/json;charset=utf-8")

    public TakeExpressResult auditRegister(@RequestParam String registerid,@RequestParam String status,@RequestParam String content,HttpServletRequest re){
        return managerRegisterService.checkRegister(registerid,status,content,re);
    }

    /**
     * 显示用户权限申请分页
     * */
    @RequestMapping(value = "/userApplications",produces = "application/json;charset=utf-8")
    public DataTableResult showUserApplications(Model model, @RequestParam(defaultValue = "1") Integer start, @RequestParam(defaultValue = "10") Integer length, Integer draw, HttpServletRequest request){
        //之所以要用request方式接收前端传回的search参数，是因为前端传回的参数名为 search[value]
        //很难直接定义String search[value]
        String search = request.getParameter("search[value]");
        DataTableResult dataTableResult = manageUserService.UserJurisdictionApplicationShow(start,length,search);
        dataTableResult.setDraw(draw);
        dataTableResult.setRecordsFiltered(dataTableResult.getRecordsTotal());
        return dataTableResult;
    }

    @RequestMapping(value="/managerRegister",produces = "application/json;charset=utf-8")
    public DataTableResult showManagerRegister(@RequestParam(defaultValue = "1") Integer start,@RequestParam(defaultValue = "10") Integer length, Integer draw,HttpServletRequest request){
        String search = request.getParameter("search[value]");
        DataTableResult result = manageUserService.registerUser(start,length,search);
        result.setDraw(draw);
        result.setRecordsFiltered(result.getRecordsTotal());
        return result;
    }
    @RequestMapping(value="/scantionUserShow",produces = "application/json;charset=utf-8")
    public DataTableResult showApplyScantionUser(@RequestParam(defaultValue = "1") Integer start, @RequestParam(defaultValue = "10") Integer length, Integer draw,HttpServletRequest request){
        //之所以要用request方式接收前端传回的search参数，是因为前端传回的参数名为 search[value]
        //很难直接定义String search[value]
        String search = request.getParameter("search[value]");
        DataTableResult dataTableResult = manageUserService.ScantionUser(start,length,search);
        dataTableResult.setDraw(draw);
        dataTableResult.setRecordsFiltered(dataTableResult.getRecordsTotal());
        return dataTableResult;
    }
    @RequestMapping(value = "/deleteApplicationUser",produces = "application/json;charset=utf-8")
    public TakeExpressResult deleteApplicationUser(String id){
        if (manageUserService.deleteApplicationUser(id)==1){
            return TakeExpressResult.ok();
        }
        return TakeExpressResult.build(400,"删除失败");
    }
}
