package com.express.web.controller.backstage;
import com.express.domain.Manager;
import com.express.utils.JsonUtils;
import com.express.utils.RedirecUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/4/17 12:07
 */
@Controller
@RequestMapping("/backstage")
public class ManagerBaseController {
    protected Manager manager = null;
    /**
     * 数据预处理
     * @param session 会话
     * 注解注释的方法会在此controller每个方法执行前被执行，因此在未登录前访问继承该控制
     * 时首先会访问这个方法.
     */
    @ModelAttribute
    public void getCurrentManager(HttpServletRequest request,HttpServletResponse response, HttpSession session){
        String token = (String) session.getAttribute("Manager_TOKEN");
        String json = (String) session.getAttribute(token);
        if (StringUtils.isBlank(json)){
            RedirecUtil.redirect(request,response,"./admin/page/login");
            return;
        }
        manager = JsonUtils.jsonToPojo(json, Manager.class);
    }
}
