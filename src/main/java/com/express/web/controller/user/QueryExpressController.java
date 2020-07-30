package com.express.web.controller.user;

import com.express.domain.UserExpress;
import com.express.service.user.UserExpressService;
import com.express.utils.ExpressUtils;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author P1n93r
 */
@RestController
public class QueryExpressController extends UserBaseController {
    @Resource
    UserExpressService service;

    /**
     * 调用工具类查询快递信息
     * @param no 快递单号
     * @return 带有快递查询结果信息的json数据
     */
    @RequestMapping(value = "/queryExpress",produces = "application/json;charset=utf-8")
    public String queryExpress(@RequestParam String no){
        //调用工具类
        String result = ExpressUtils.query(no);
        //将字符串转成json对象（嘿这个工具类还挺好用=-=）
        JSONObject resultJson = JSONObject.fromObject(result);
        resultJson.put("alert","0".equals(resultJson.get("status"))? "success":"danger");
        resultJson.put("message","查询状态："+resultJson.get("msg"));
        return resultJson.toString();
    }


    /**
     * @return 用户历时查询订单号，以数组的形式返回
     */
    @RequestMapping(value = "/expressNoCache",produces = "application/json;charset=utf-8")
    public String expressNocache() throws Exception {
        String result = service.findUserExpress(userlogin.getUserId());
        return result;
    }


    /**
     * 删除用户的某条历史搜索记录
     * @return
     */
    @RequestMapping(value = "/deleteUserExpressById",produces = "application/json;charset=utf-8")
    public String deleteUserExpressById(@RequestParam Integer id) throws Exception {
        Boolean judge = service.deleteUserExpressById(id);
        JSONObject result = new JSONObject();
        result.put("result",judge? true:false);
        return result.toString();
    }

    /**
     * 添加一条用户历史搜索记录
     * @return 插入是否成功
     */
    @RequestMapping(value = "/addUserExpress",produces = "application/json;charset=utf-8")
    public String addUserExpress(UserExpress userExpress) throws Exception {
        //设置uid
        userExpress.setUid(userlogin.getUserId());
        //验证器验证
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<UserExpress>> validateRes = validator.validate(userExpress);
        //如果验证通过
        Boolean judge=false;
        JSONObject result = new JSONObject();
        if(validateRes.isEmpty()){
            //执行service的插入
            judge = service.insertUserExpress(userExpress);
        }
        result.put("result",judge? true:false);
        return result.toString();
    }


}
