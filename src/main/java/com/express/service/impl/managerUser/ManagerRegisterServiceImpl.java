package com.express.service.impl.managerUser;
import com.express.common.TakeExpressResult;
import com.express.common.DelectPic;
import com.express.domain.Register;
import com.express.domain.User;
import com.express.domain.UserExample;
import com.express.domain.UserLogin;
import com.express.mapper.RegisterMapper;
import com.express.mapper.UserLoginMapper;
import com.express.mapper.UserMapper;
import com.express.service.managerUser.ManagerRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.express.utils.EmailUtils;
/**
 * @Author fyzn12
 * @Date 2020/3/16 11:59
 * @Version 1.0
 */

@Service
public class ManagerRegisterServiceImpl implements ManagerRegisterService {
    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Override
    public TakeExpressResult checkRegister(String registerid, String status, String content, HttpServletRequest request) {
        try {
            //获取前端传回的状态码 0代表审核通过，1代表审核失败
            Register register = registerMapper.selectByPrimaryKey(Integer.parseInt(registerid));
            if (register==null){
                return TakeExpressResult.build(400,"注册用户不存在");
            }
            //审核通过，将注册数据插入user表
            if (status.equals("0")) {
                //注册表没有注册信息，预防前端注入
                if (register.getLoginId() == null) {
                    return TakeExpressResult.build(400,"注册用户不存在");
                }
                // path = srcPath.substring()
                User user = new User();
                UserLogin userLogin = new UserLogin();
                //设置学生证图片
                user.setStuPic(register.getStuPic());
                user.setEmail(register.getEmail());
                user.setPhone(register.getPhone());
                user.setStatus("0");
                //设置需求积分
                user.setDemandScore(100);
                //设置代取积分
                user.setFetchScore(100);
                //设置默认头像
                user.setPic("static/user/images/dome3.jpg");
                //将用户加入user表
                userMapper.insertSelective(user);
                /*
                * 先插入user表，方便获取loginid
                * */
                UserExample userExample = new UserExample();
                UserExample.Criteria criteria = userExample.createCriteria();
                criteria.andPhoneEqualTo(register.getPhone());
                List<User> check =  userMapper.selectByExample(userExample);
                Integer userid = check.get(0).getUserId();
                System.out.println("userid     :"+userid);
                userLogin.setUserId(userid);
                userLogin.setLoginId(register.getLoginId());
                userLogin.setLoginPwd(register.getLoginPwd());
                System.out.println(userLogin.toString());
                //同步插入用户登录表
                 userLoginMapper.insert(userLogin);
                //1.注册成功通过邮箱发送消息给用户
                EmailUtils.sendEmail(register.getEmail(),"邮件主题","尊敬的用户，你注册校园快递代取系统注册成功，欢迎你使用该系统！");
                registerMapper.deleteByPrimaryKey(Integer.parseInt(registerid));
                return TakeExpressResult.ok("用户注册成功");
            }
            //代表管理员审核失败
            if (status.equals("1")) {
                //获取绝对路径
                String path = request.getServletContext().getRealPath("");
                String srcPath = register.getStuPic();
                path = path + srcPath;
                //2.删除用户上传到服务器的学生证的图片
                DelectPic.delect(path);
                //3.删除用户注册表记录
                registerMapper.deleteByPrimaryKey(Integer.parseInt(registerid));
                try {
                    EmailUtils.sendEmail(register.getEmail(),"邮件主题","尊敬的用户，你注册校园快递代取系统失败，失败原因为："+content);
                }catch (Exception e){
                    return TakeExpressResult.build(500,"用户注册邮箱不存在！");
                }

                return TakeExpressResult.build(200,"用户注册失败");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
