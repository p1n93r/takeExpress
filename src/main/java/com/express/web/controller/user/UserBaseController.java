package com.express.web.controller.user;


import com.express.domain.User;
import com.express.domain.UserLogin;
import com.express.mapper.UserMapper;
import com.express.utils.JsonUtils;
import com.express.utils.RedirecUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author P1n93r
 * USer模块的基础类
 */
@Controller
@RequestMapping("/user")
public class UserBaseController {
    /**
     * 当前登陆用户的登录数据（无密码）
     */
    protected UserLogin userlogin;
    protected User user = null;
    @Autowired
    private UserMapper userMapper;
    /**
     * 数据预处理
     * @param session 会话
     */
    @ModelAttribute
    public void getCurrentUser(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String token = (String) session.getAttribute("User_TOKEN");
        String json = (String) session.getAttribute(token);
        if (StringUtils.isNotEmpty(json)){
            userlogin = JsonUtils.jsonToPojo(json,UserLogin.class);
            user = userMapper.selectByPrimaryKey(userlogin.getUserId());
            return;
        }else {
            RedirecUtil.redirect(request,response,"./login");
            return;
        }
    }
}
