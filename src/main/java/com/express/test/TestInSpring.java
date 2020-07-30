package com.express.test;


import com.express.utils.EmailUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.mail.MessagingException;

/**
 * 基于Spring环境的单元测试
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
        "classpath:spring/*.xml"
})

public class TestInSpring {

    /**
     * 测试发送邮件
     */
    @Test
    public void testSendEmail() throws MessagingException {
        EmailUtils.sendEmail("1814819033@qq.com","xxx","喝热水，不要着凉，不要吃辛辣食品！！！~~");
    }

    @Test
    public void test333() throws MessagingException {
        EmailUtils.sendEmail("1814819033@qq.com","xxx","喝热水，不要着凉，不要吃辛辣食品！！！~~");
    }



}
