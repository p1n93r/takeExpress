package com.express.service.impl.user;


import com.express.common.TakeExpressResult;
import com.express.domain.*;
import com.express.mapper.RegisterMapper;
import com.express.mapper.UserMapper;
import com.express.service.CheckService;
import com.express.service.PictureService;
import com.express.service.user.RegisterService;
import com.express.utils.DateUtils;
import com.express.utils.Validator.RegisterValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private CheckService checkService;
    public boolean check(String loginid,String phone) {
        RegisterExample registerExample = new RegisterExample();
        RegisterExample.Criteria criteria2 = registerExample.createCriteria();
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andAlipayBetween(loginid,phone);
        List<User> user = userMapper.selectByExample(userExample);
        List<Register> user2 = registerMapper.selectByExample(registerExample);
        if (user == null || user.isEmpty() && user2 == null || user2.isEmpty()){
            return true;
        }
        return false;
    }
    @Override
    public TakeExpressResult register(RegisterValidator registerValidator, HttpServletRequest request) {
        /*
        * 首先判断loginid是否已被注册
        * 再次判断手机号是否已被注册
        * 最后判断邮箱是否被注册
        * */
        if (!checkService.checkLoginId(registerValidator.getLoginid().trim())){
            return TakeExpressResult.build(400,"账号已被注册");
        }
        System.out.println("phone  :"+checkService.checkPhone(registerValidator.getPhone().trim()));
        //判断手机号
        if (checkService.checkPhone(registerValidator.getPhone().trim())){
            return TakeExpressResult.build(400,"手机号已被注册");
        }
        if (!checkService.checkEmail(registerValidator.getEmail().trim())){
            return TakeExpressResult.build(400,"邮箱已被注册");
        }
        HttpSession session = request.getSession();
        //发送短信时间
        Date sendTime = (Date)session.getAttribute(registerValidator.getPhone()+"Time");
        //判断和当前时间相差多少秒
        //判断和当前时间相差多少秒
        if (session.getAttribute(registerValidator.getPhone())==null || (int)session.getAttribute(registerValidator.getPhone())==0){
            return TakeExpressResult.build(400,"请先获取验证码");
        }
        System.out.println("session的值"+session.getAttribute(registerValidator.getPhone()));
        if ((registerValidator.getCode().trim()).equals(String.valueOf(session.getAttribute(registerValidator.getPhone())).trim())){
            Double seconds = DateUtils.calculatetimeGapSecond(sendTime, new Date());
            if (seconds>60){
                return TakeExpressResult.build(400,"验证码过期");
            }
            if (registerValidator.getPassword().trim().equals(registerValidator.getPasswordcheck().trim())){
                //将图片上传到服务器
                String path = request.getServletContext().getRealPath("static/user/picture/"+registerValidator.getLoginid());
                Map<String,Object> map = pictureService.uploadPicture(registerValidator.getStupic(),path);
                System.out.println(map);
                if (map.get("status")=="0" || ((Integer)map.get("status"))==0){
                    String stuPic = (String) map.get("url");
                    //密码进行md5加密
                    String password = DigestUtils.md5DigestAsHex(registerValidator.getPassword().trim().getBytes());
                    Register register = new Register(stuPic, null,registerValidator.getLoginid(), password,registerValidator.getPhone(), registerValidator.getEmail(),"1");
                    //将注册信息写入注册表
                    registerMapper.insertSelective(register);
                    return TakeExpressResult.ok();
                }else{
                    return TakeExpressResult.build(400,(String) map.get("status"));
                }
            }
        }
        return TakeExpressResult.build(400,"验证码错误");
    }
}
