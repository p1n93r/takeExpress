package com.express.test;
import com.express.service.impl.manager.LogServiceImpl;
import com.express.service.manager.LogService;
import com.express.service.user.UserExpressService;
import com.express.utils.EmailUtils;
import com.express.utils.ExpressUtils;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import javax.annotation.Resource;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


/**
 * 基于Spring环境的单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "classpath:spring/*.xml"
})

public class BasedSpringTest {
    @Resource
    private WebApplicationContext webApplicationContext;

    @Resource
    UserExpressService service;

    @Resource
    LogService logService;



    /**
     * 测试发送邮件
     */
    @Test
    public void testSendEmail() throws MessagingException {
        EmailUtils.sendEmail("1725367974@qq.com","测试","好想开学~~");
    }


    /**
     * 测试发送http
     */
    @Test
    public void testSendHttp(){
        //调用工具类
        String result = ExpressUtils.query("4304234557129");
        JSONObject resultJson = JSONObject.fromObject(result);
    }

    @Test
    public void testUserExpressService() throws Exception {
        String result = service.findUserExpress(1);
        System.out.println(result);
    }


    @Test
    public void testLog() throws Exception {
        ArrayList<Integer> val = new ArrayList<>();
        val.add(2);
        val.add(3);
        val.add(4);
        logService.deleteSomeLog(val);
    }





}
