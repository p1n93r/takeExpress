package com.express.service.impl.manager;

import com.baidu.aip.face.AipFace;
import com.express.common.TakeExpressResult;
import com.express.common.DelectPic;
import com.express.domain.Manager;

import com.express.domain.ManagerExample;
import com.express.mapper.ManagerMapper;
import com.express.mapper.UserMapper;
import com.express.service.CheckService;
import com.express.service.impl.CheckServiceImpl;
import com.express.service.manager.PersonalCenterManageService;
import com.express.service.PictureService;
import com.express.utils.*;
import com.express.utils.Validator.ModifyBaseInfoValidator;
import com.express.utils.Validator.ModifyPasswordValidator;
import com.express.utils.Validator.ModifyPhoneValidator;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/19 9:58
 */
@Service
public class PersonalCenterManageServiceImpl  implements PersonalCenterManageService {
    private static String accessToken;
    private static String result;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private CheckService checkService;
    @Override
    public TakeExpressResult modifyPassword(ModifyPasswordValidator modifyPasswordValidator , HttpServletRequest request) {
        try{
            //判断验证码
            HttpSession session = request.getSession();
            if (session == null) {
                return TakeExpressResult.build(400, "请先获取验证码");
            }
            ManagerExample managerExample = new ManagerExample();
            ManagerExample.Criteria criteria = managerExample.createCriteria();
            criteria.andLoginIdEqualTo(modifyPasswordValidator.getLoginid());
            criteria.andPhoneEqualTo(modifyPasswordValidator.getPhone());
            List<Manager> managerList = managerMapper.selectByExample(managerExample);
            if (managerList==null || managerList.isEmpty()){
                return TakeExpressResult.build(400,"管理员不存在");
            }
            //获取以电话号码为建，验证码为值的session
            Object sessVerificationCode =  session.getAttribute(modifyPasswordValidator.getPhone().trim());
            if (sessVerificationCode == null) {
                return TakeExpressResult.build(400, "请先获取验证码");
            }
            //主要是验证服务器保存的验证码和前端传回的验证码是否相等
            /*
             * 这里不能直接转为String，在获取验证码的时候存入session的值就是int型，直接转为String会报错
             * */
            if ((String.valueOf((int)sessVerificationCode).trim()).equals(modifyPasswordValidator.getCode().trim())) {
                //验证验证码是否过期
                //发送短信时间
                Date sendTime = (Date) session.getAttribute(modifyPasswordValidator.getPhone() + "Time");
                //判断和当前时间相差多少秒
                Double seconds = DateUtils.calculatetimeGapSecond(sendTime, new Date());
                if (seconds > 60) {
                    return TakeExpressResult.build(400, "验证码过期");
                }
                String password = DigestUtils.md5DigestAsHex(modifyPasswordValidator.getPassword().getBytes());
                if (password.equals(managerList.get(0).getLoginPwd())){
                    return TakeExpressResult.build(400,"修改密码不能和之前密码相同！");
                }
                managerList.get(0).setLoginPwd(password);
                managerMapper.updateByPrimaryKey(managerList.get(0));
                if (request.getSession() != null) {
                    //生成一个随机的uuid
                    UUID uuid = UUID.randomUUID();
                    String token = uuid.toString();
                    request.getSession().setAttribute("Manager_TOKEN",token);
                    managerList.get(0).setLoginPwd(null);
                    request.getSession().setAttribute(token,JsonUtils.objectToJson(managerList.get(0)));
                    request.getSession().setMaxInactiveInterval(1800);
                }
                return TakeExpressResult.ok("修改密码成功");
            }
            return TakeExpressResult.build(400,"验证码不正确");

        }catch (Exception e){
            System.out.println("异常信息"+e.getMessage());
            return TakeExpressResult.build(400,"修改密码异常");
        }
    }

    @Override
    public TakeExpressResult modifyPhone(ModifyPhoneValidator modifyPhoneValidator, HttpServletRequest request) {
        /*
        * 首先需要明确，修改手机号需要接收短信验证码，验证码正确即可修改
        * 通过验证框架这里我们就不用自己再验证
        * 首先通过managerid判断用户是否存在
        * 再通过session判断是否该号码获取过验证码
        * */

        Integer managerid = Integer.parseInt(modifyPhoneValidator.getManagerid());
        Manager manager = managerMapper.selectByPrimaryKey(managerid);
        if (manager==null){
            return TakeExpressResult.build(400,"请求修改管理员不存在");
        }
        HttpSession session = request.getSession();
        //获取以电话号码为建，验证码为值的session
        Object sessVerificationCode =  session.getAttribute(modifyPhoneValidator.getPhone());
        if (sessVerificationCode == null) {
            return TakeExpressResult.build(400, "请先获取验证码");
        }
        /*
         * 主要是验证服务器保存的验证码和前端传回的验证码是否相等
         * 这里不能直接转为String，在获取验证码的时候存入session的值就是int型，直接转为String会报错
         * */
        if ((String.valueOf((int)sessVerificationCode).trim()).equals(modifyPhoneValidator.getCode().trim())) {
            //验证验证码是否过期
            //发送短信时间
            Date sendTime = (Date) session.getAttribute(modifyPhoneValidator.getPhone() + "Time");
            //判断和当前时间相差多少秒
            Double seconds = DateUtils.calculatetimeGapSecond(sendTime, new Date());
            if (seconds > 60) {
                return TakeExpressResult.build(400, "验证码过期");
            }
            manager.setPhone(modifyPhoneValidator.getPhone());
            managerMapper.updateByPrimaryKey(manager);
            if (request.getSession() != null) {
                //生成一个随机的uuid
                UUID uuid = UUID.randomUUID();
                String token = uuid.toString();
                request.getSession().setAttribute("Manager_TOKEN",token);
                manager.setLoginPwd(null);
                request.getSession().setAttribute(token,JsonUtils.objectToJson(manager));
                request.getSession().setMaxInactiveInterval(1800);
            }
            return TakeExpressResult.ok("修改电话号码成功");
        }
        return TakeExpressResult.build(400,"验证码不正确");
    }
    @Override
    public TakeExpressResult modifyManagerBaseInfo(ModifyBaseInfoValidator modifyBaseInfoValidator, HttpServletRequest request) {
        try{
            //之所以用try是因为防止前端通过xss传输假数据，在数据表中managerid为int型，
            ManagerExample managerExample = new ManagerExample();
            ManagerExample.Criteria criteria = managerExample.createCriteria();
            criteria.andLoginIdEqualTo(modifyBaseInfoValidator.getLoginid());
            List<Manager> managerList = managerMapper.selectByExample(managerExample);
            if (managerList.isEmpty()){
                return TakeExpressResult.build(400,"请求管理员不存在");
            }
            Manager manager = managerList.get(0);
            if (!modifyBaseInfoValidator.getContent().equals(manager.getSignature())){
                manager.setSignature(modifyBaseInfoValidator.getContent());
            }
            if (!modifyBaseInfoValidator.getNickname().equals(manager.getNickname())){
                manager.setNickname(modifyBaseInfoValidator.getNickname());
            }
            if (!modifyBaseInfoValidator.getEmail().equals(manager.getEmail())){
                /*通过jsr303验证说明邮箱格式正确*/
                if (checkService.checkEmail(modifyBaseInfoValidator.getEmail())){
                    manager.setEmail(modifyBaseInfoValidator.getEmail());
                }else {
                    return TakeExpressResult.build(400,"邮箱已被其他用户占用！");
                }
            }
            if (modifyBaseInfoValidator.getHandpic()==null || modifyBaseInfoValidator.getHandpic().isEmpty()){
                managerMapper.updateByPrimaryKey(manager);
                return TakeExpressResult.ok("修改成功");
            }else {

                //将图片上传到服务器
                String path = request.getServletContext().getRealPath("static/manager/picture/" + manager.getLoginId());
                Map<String, Object> map = pictureService.uploadPicture(modifyBaseInfoValidator.getHandpic(), path);
                if (map.get("status") == "0" || ((Integer) map.get("status")) == 0) {
                    //删除原来的头像
                    String oldPath = manager.getPic();
                    System.out.println("path  :"+oldPath);
                    DelectPic.delect(oldPath);
                    //添加新的图片
                    manager.setPic((String) map.get("url"));
                    try {
                        managerMapper.updateByPrimaryKey(manager);
                        //修改session中的记录
                        if (request.getSession() != null) {
                            //生成一个随机的uuid
                            UUID uuid = UUID.randomUUID();
                            String token = uuid.toString();
                            request.getSession().setAttribute("Manager_TOKEN",token);
                            manager.setLoginPwd(null);
                            request.getSession().setAttribute(token,JsonUtils.objectToJson(manager));
                            request.getSession().setMaxInactiveInterval(1800);
                        }
                    }catch (Exception e){
                        e.getMessage();
                        System.out.println("修改数据库异常"+e.getMessage());
                    }
                    return TakeExpressResult.ok("修改成功");
                }
            }
            return TakeExpressResult.build(400,"修改信息失败");
        }catch (Exception e){
            System.out.println("异常原因："+e.getMessage());
            return TakeExpressResult.build(400,"请求异常");
        }
    }

    @Override
    public Map<String,String> facesetAddUser(AipFace client,Manager manager, String img) {
        //上传到百度云的时候，用户id就是登录id,包括
        //添加人脸库用户访问url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/add";
        Map<String, Object> map = new HashMap<String, Object>();
        Map<String, String> resultmap = new HashMap<String, String>();
        map.put("image", img);
        //用户组id
        map.put("group_id", "take_express");
        //用户id,//或者是管理员的id
        map.put("user_id",manager.getManagerId());
        map.put("user_info",manager.getNickname());
        //活体检测控制
        map.put("liveness_control", "NORMAL");
        //图片类型
        map.put("image_type", "BASE64");
        //图片值量控制（一般值量控制），具体查看API文档
        map.put("quality_control", "LOW");
        //调用百度云的工具类GsonUtils
        try {
            String param = JsonUtils.toJson(map).toString();
            System.out.println("param: "+param);
            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            //获取accessToken，具体可以查看百度云API
            accessToken = GetToken.getAuth().toString();
            result = HttpUtil.post(url, accessToken, "application/json", param);
            JSONObject fromObject = JSONObject.fromObject(result);
            //api的返回值
            Object errormsg = fromObject.get("error_msg");
            if (errormsg.toString().equals("SUCCESS")) {
                resultmap.put("status","0");
                return resultmap;
            }
            resultmap.put("status","1");
            return resultmap;
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
