package com.express.web.controller.user;
import com.express.common.TakeExpressResult;
import com.express.service.managerUser.ManageUserService;
import com.express.utils.Validator.UserJurisdictionApplicationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/4/26 11:25
 */
@RestController
public class AplicationJurisdictionController extends UserBaseController {
    @Autowired
    private ManageUserService manageUserService;
    /*
     * UserJurisdictionApplication用户权限申请
     * */

    @RequestMapping(value = "/JurisdictionApplication",produces = "application/json;charset=utf-8")
    public TakeExpressResult UserJurisdictionApplicationAction(@Valid UserJurisdictionApplicationValidator userapp, HttpServletRequest request)  {
        if (user==null){
            return TakeExpressResult.build(400,"请先登录");
        }
        return manageUserService.UserJurisdictionApplication(userapp,request);
    }
}
