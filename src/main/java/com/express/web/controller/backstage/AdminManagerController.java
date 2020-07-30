package com.express.web.controller.backstage;

import com.baidu.aip.face.AipFace;
import com.express.common.TakeExpressResult;
import com.express.domain.Manager;
import com.express.service.CheckService;
import com.express.service.manager.PersonalCenterManageService;
import com.express.utils.JsonUtils;
import com.express.utils.Validator.ModifyBaseInfoValidator;

import com.express.utils.Validator.ModifyPasswordValidator;
import com.express.utils.Validator.ModifyPhoneValidator;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/20 10:36
 */
@RestController
@RequestMapping("/admin")
public class AdminManagerController extends ManagerBaseController{
    /*
    * 为了不想去前端一个个找ajax的访问路径，这里我直接用@RequestMapping("/admin")覆盖继承类的@RequestMapping路径
    * */

    @Autowired
    private PersonalCenterManageService personalCenterManageService;
    @Autowired
    private CheckService checkService;
    /*
     * 后台个人中心信息管理
     * */

    @RequestMapping(value = "/modifyPassword",produces = "application/json;charset=utf-8")
    public TakeExpressResult modifyPassword(@Valid ModifyPasswordValidator modifypassword, HttpServletRequest request)  {
        return personalCenterManageService.modifyPassword(modifypassword,request);
    }

    /*
    * 修改基本信息的请求路径就是一个
    * */

    @RequestMapping(value = "/modifyphone",produces = "application/json;charset=utf-8")
    public TakeExpressResult modifyPhone(@Valid ModifyPhoneValidator modifyPhoneValidator , HttpServletRequest request) {

        return personalCenterManageService.modifyPhone(modifyPhoneValidator,request);
    }
    /*
    * 前端请求下面路径，经过判断选择是否修改电话和email
    * */

    @RequestMapping(value = "/modifyManagerBaseInfo",produces = "application/json;charset=utf-8")
    public TakeExpressResult modifyManagerBaseInfo(@Valid ModifyBaseInfoValidator modifyBaseInfoValidator, HttpServletRequest request) {
        try{
            return personalCenterManageService.modifyManagerBaseInfo(modifyBaseInfoValidator,request);
        }catch (Exception e){
            System.out.println("请求异常：  "+e.getMessage());
            return TakeExpressResult.build(400,"修改异常");
        }

    }
    @RequestMapping(value = "/modifyphonecheck",produces = "application/json;charset=utf-8")
    public TakeExpressResult checkPhone(@RequestParam String phone){
        if (phone.isEmpty()){
            return TakeExpressResult.build(400,"号码格式不正确");
        }
        if (checkService.checkManagerBindPhone(phone.trim())){
            return TakeExpressResult.ok();
        }
        return TakeExpressResult.build(400,"号码已被注册");
    }
    @RequestMapping(value = "/bindface",produces = "application/json;charset=utf-8")
    public TakeExpressResult BindFace(@RequestParam("img") String img,HttpServletRequest request){
        /*
        * 首先根据session判断管理员的
        * 再获取管理员的信息
        * */
        if (StringUtils.isBlank(img)){
            return TakeExpressResult.build(400,"未检测到人脸");
        }
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("Manager_TOKEN");
        if (token==null || StringUtils.isBlank(token)){
            return TakeExpressResult.build(400,"请先登录");
        }
        String json = (String) session.getAttribute(token);
        Manager manager = JsonUtils.jsonToPojo(json,Manager.class);
        if (manager != null){
            String APP_ID = "18730696";
            String API_KEY = "dZxT165BCRyEpIEVp5pCfWBu";
            String SECRET_KEY = "DFTq1NtHLSD3PWybdHqq6MDZL59ku2tx";
            AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
            Map<String,String> map = personalCenterManageService.facesetAddUser(client,manager,img);
            if (map.get("status").equals("0")){
                return TakeExpressResult.ok();
            }
        }
        return TakeExpressResult.build(400,"人脸识别失败");

    }
}
