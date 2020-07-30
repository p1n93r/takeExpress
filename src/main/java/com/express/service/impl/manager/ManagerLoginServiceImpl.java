package com.express.service.impl.manager;

import com.express.common.FaceResult;
import com.express.common.TakeExpressResult;
import com.express.common.faceErroeResult;
import com.express.domain.*;
import com.express.mapper.ManagerMapper;
import com.express.mapper.UserMapper;
import com.express.service.manager.ManagerLoginService;
import com.express.utils.*;
import com.express.utils.Validator.LoginValidator;
import com.express.utils.Validator.PhoneloginValidator;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/22 17:46
 */
@Service
public class ManagerLoginServiceImpl implements ManagerLoginService {
    private static String accessToken;
    private static String result;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public Map<String,String> faceLogin(HttpServletRequest request, HttpServletResponse response, String img) {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/search";
        Map<String, Object> map = new HashMap<>();
        Map<String, String> resultmap = new HashMap<>();
        map.put("image", img);
        //一般活体要求
        map.put("liveness_control", "NORMAL");
        //从指定的组中找
        map.put("group_id_list", "take_express");
        map.put("image_type", "BASE64");
        //图片值量
        map.put("quality_control", "LOW");
        try {
            String param = JsonUtils.toJson(map);
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            accessToken = GetToken.getAuth().toString();
            result = HttpUtil.post(url, accessToken, "application/json", param);
            faceErroeResult faceErroeResult = new faceErroeResult();
            faceErroeResult = JsonUtils.jsonToPojo(result.toString(), faceErroeResult.class);
            if (faceErroeResult.getError_code().equals("222207")){
                resultmap.put("msg","请先绑定人脸识别");
                resultmap.put("status","1");
                return resultmap;
            }
            JSONObject fromObject = JSONObject.fromObject(result);
            JSONObject resultscore = fromObject.getJSONObject("result");
            JSONArray jsonArray = resultscore.getJSONArray("user_list");
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject object = (JSONObject) jsonArray.get(i);
                double resultList = object.getDouble("score");
                //如果图片相似度达到百分之90以上则验证成功
                if (resultList >= 90) {
                    resultmap.put("status","0");
                    //将json对象转为pojo
                    FaceResult user = JsonUtils.jsonToPojo(object.toString(), FaceResult.class);
                    //生成token
                    //生成一个随机的uuid
                    UUID uuid = UUID.randomUUID();
                    String token = uuid.toString();
                    //把用户信息写入session
                    //把用户的密码清空，为了安全
                    Manager manager = new Manager();
                    manager.setManagerId(Integer.parseInt(user.getUser_id()));
                    manager = managerMapper.selectByPrimaryKey(Integer.parseInt(user.getUser_id()));
                    manager.setLoginPwd(null);
                    HttpSession session = request.getSession();
                    session.setAttribute("Manager_TOKEN",token);
                    session.setAttribute(token,JsonUtils.objectToJson(manager));
                    session.setMaxInactiveInterval(1800);
                    //把token写入cookie
                    CookieUtils.setCookie(request, response, "Manager_TOKEN", token);
                    return resultmap;
                }
            }
            resultmap.put("msg",faceErroeResult.getError_msg());
            resultmap.put("status","1");
            return resultmap;
        } catch (Exception e ){
            e.printStackTrace();
            System.out.println("异常  "+e.getMessage());
            return null;
        }

    }

    @Override
    public TakeExpressResult Login(LoginValidator loginValidator, HttpServletRequest request, HttpServletResponse response) {
        try{
            ManagerExample managerExample = new ManagerExample();
            ManagerExample.Criteria criteria = managerExample.createCriteria();
            // 初始化criteria
            criteria.andLoginIdEqualTo(loginValidator.getId().trim());
            List<Manager> managerList = managerMapper.selectByExample(managerExample);
            if (managerList ==null || managerList.isEmpty()){
                return TakeExpressResult.build(400,"管理员不存在！");
            }
            //验证密码是否正确
            Manager manager = managerList.get(0);
            if (!DigestUtils.md5DigestAsHex(loginValidator.getPassword().trim().getBytes()).equals(manager.getLoginPwd())){
                return TakeExpressResult.build(400, "用户名或密码错误");
            }
            //剩下就是密码和用户名正确
            //生成一个随机的uuid，用于作为token
            UUID uuid = UUID.randomUUID();
            String token = uuid.toString();
            //首先把用户密码设为空（为了安全考虑）
            manager.setLoginPwd(null);
            HttpSession session = request.getSession();
            session.setAttribute("Manager_TOKEN",token);
            session.setAttribute(token,JsonUtils.objectToJson(manager));
            session.setMaxInactiveInterval(1800);
            CookieUtils.setCookie(request,response,"Manager_TOKEN",token);
            return TakeExpressResult.ok(token);
        }catch (Exception e){
            System.out.println("异常信息： "+e.getMessage());
            return TakeExpressResult.build(400,"登录异常");
        }
    }

    @Override
    public TakeExpressResult PhoneLogin(PhoneloginValidator phoneloginValidator, HttpServletRequest request, HttpServletResponse response, HttpSession session) {
        try {
            //判断是否取得验证码
            if (session == null) {
                return TakeExpressResult.build(400, "请先获取验证码");
            }
            //获取以电话号码为建，验证码为值的session
            Object sessVerificationCode =  session.getAttribute(phoneloginValidator.getPhone().trim());
            if (sessVerificationCode == null) {
                return TakeExpressResult.build(400, "请先获取验证码");
            }
            System.out.println(sessVerificationCode + "   :  " + phoneloginValidator.getCode().trim());
            //主要是验证服务器保存的验证码和前端传回的验证码是否相等
            /*
             * 这里不能直接转为String，在获取验证码的时候存入session的值就是int型，直接转为String会报错
             * */
            if ((String.valueOf((int)sessVerificationCode).trim()).equals(phoneloginValidator.getCode().trim())) {
                //验证验证码是否过期
                //发送短信时间
                Date sendTime = (Date) session.getAttribute(phoneloginValidator.getPhone() + "Time");
                //判断和当前时间相差多少秒
                Double seconds = DateUtils.calculatetimeGapSecond(sendTime, new Date());
                if (seconds > 60) {
                    return TakeExpressResult.build(400, "验证码过期");
                }
                ManagerExample managerExample = new ManagerExample();
                ManagerExample.Criteria criteria = managerExample.createCriteria();
                criteria.andPhoneEqualTo(phoneloginValidator.getPhone());
                List<Manager> manager = managerMapper.selectByExample(managerExample);
                //在这里主要是验证别人通过正确电话号码获取验证码，错误电话号码登录的情况
                if (manager == null || manager.isEmpty()) {
                    return TakeExpressResult.build(400, "电话未注册");
                }
                //生成一个随机的uuid，用于作为token
                UUID uuid = UUID.randomUUID();
                String token = uuid.toString();
                //把用户信息写入redis
                Manager manager1 = manager.get(0);
                manager1.setLoginPwd(null);
                session.setAttribute("Manager_TOKEN", token);
                session.setAttribute(token, JsonUtils.objectToJson(manager1));
                session.setMaxInactiveInterval(1800);
                //把token写入cookie
                CookieUtils.setCookie(request, response, "Manager_TOKEN", token);
                System.out.println("完成登录验证");
                return TakeExpressResult.ok(token);

            }
        }catch (Exception e){
            System.out.println("check  "+e.getMessage());
            return TakeExpressResult.build(400,"登录验证失败");
        }
        return TakeExpressResult.build(400,"验证码不正确");
    }


}
