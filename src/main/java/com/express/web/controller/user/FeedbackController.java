package com.express.web.controller.user;

import com.express.domain.Feedback;
import com.express.domain.FeedbackExample;
import com.express.domain.UserExpress;
import com.express.domain.UserLogin;
import com.express.service.user.FeedbackService;
import com.express.utils.EmailUtils;
import com.express.utils.JsonUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.text.resources.cldr.es.FormatData_es_419;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * @author cassie
 */

@RestController
public class FeedbackController extends UserBaseController {
    @Autowired
    FeedbackService service;


    /**
     * 如果service执行成功，就返回一条json数据给前台：{'result':true}，否则{'result':false}
     * @param feedback
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/userPutFeedback",produces = "application/json;charset=utf-8")
    public String putFeedback(Feedback feedback) {
        Integer uid = userlogin.getUserId();
        feedback.setUid(uid);
        feedback.setCreateTime(LocalDateTime.now().toString());
        //在调用service之前需要进行参数校验
        //验证我们必须要的数据是否都设置好了
        //这里的参数校验我们使用JSR303框架：HibernateValidation
        //步骤①：生成一个验证器
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        //步骤②：调用验证器验证pojo，得到验证结果
        //验证结果：如果存在验证不通过的情况，就会有一条验证不通过的结果
        //就是下面的set集合，如果是空的，就是代表全部通过了，如果不为空，就代表存在不通过的结果
        Set<ConstraintViolation<Feedback>> validateRes = validator.validate(feedback);
        JSONObject feedbackJson= new JSONObject();
        feedbackJson.put("result",false);
        if(validateRes.isEmpty()){
            int i = 0;
            try {
                i = service.insertFeedback(feedback);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(i>0){
                feedbackJson.put("result",true);
                return feedbackJson.toString();
            }
        }
        return feedbackJson.toString();
    }

}
