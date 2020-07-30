package com.express.service.impl.managerUser;
import com.express.common.DataTableResult;
import com.express.common.TakeExpressResult;
import com.express.common.DelectPic;
import com.express.domain.*;
import com.express.mapper.JurisdictionapplicationMapper;
import com.express.mapper.RegisterMapper;
import com.express.mapper.UserMapper;
import com.express.service.managerUser.ManageUserService;
import com.express.service.PictureService;
import com.express.utils.EmailUtils;
import com.express.utils.Validator.UserJurisdictionApplicationValidator;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/18 11:29
 */
@Service
public class ManageUserServiceImpl implements ManageUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private JurisdictionapplicationMapper jurisdictionapplicationMapper;
    @Autowired
    private PictureService pictureService;
    @Autowired
    private RegisterMapper registerMapper;
    /*
    * sanctiosUser 用户权限限制，制裁
    * 背景介绍
    * 管理员在页面上看到，有信誉积分过低的user，将对其限制权限
    * 在这里@param user_id 是管理员在前端获取该user时传回后台的数据
    * 这里可以根据该数据获取user的全部信息
    * 判断是否该限制user什么权限*/

    @Override
    public TakeExpressResult sanctiosUser(Integer user_id, String status,String content,HttpServletRequest request) {
        try {
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            criteria.andUserIdEqualTo(user_id);
            List<User> userList = userMapper.selectByExample(userExample);
            //如果用户的需求信誉积分或代取信誉积分过低，将会被限制权限
            if (userList==null || userList.isEmpty()){
                return TakeExpressResult.build(400,"用户不存在");
            }
            if ((userList.get(0).getStatus()).equals("3")){
                return TakeExpressResult.build(400,"当前用户已被限制全部权限");
            }
            //防止用户重复提交
            if (status.equals(userList.get(0).getStatus())){
                return TakeExpressResult.build(200,"已限制用户权限");
            }
          // 需求积分
            Integer demandScore = userList.get(0).getDemandScore();
            Integer fetchScore = userList.get(0).getFetchScore();
            // * 代取积分
            /*
            *   0  代表用户信誉好，能发布需求，也能接单，，
            *   1 代表不能发布需求
            *   2 代表不能接单
            *   3 代表只能向管理员申述
            * */
            if (demandScore>=60 && fetchScore>=60){
                return TakeExpressResult.build(400,"当前用户信用良好！");
            }
            User updateUser = new User();
            updateUser.setUserId(user_id);
            if (demandScore < 60 && status.equals("1")){
                updateUser.setStatus("1");
            }else if (fetchScore < 60 && status.equals("2")){
                updateUser.setStatus("2");
            }else if (demandScore < 60 && fetchScore < 60){
                updateUser.setStatus("3");
            }else{
                return TakeExpressResult.build(500,"限制用户权限与用户失信类型不符！");
            }
            userMapper.updateByPrimaryKeySelective(updateUser);
            /*
            * 调用邮件发送通知用户
            * */
            try{
                EmailUtils.sendEmail(userList.get(0).getEmail(),"","由于你的信誉积分过低，将限制你的部分权限，请登录查看");
            }catch (Exception e){
                return TakeExpressResult.build(501,"用户邮箱不存在！");
            }

            return TakeExpressResult.build(200,"已限制用户权限");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return TakeExpressResult.build(400,"制裁用户异常");
        }
    }
    /*
    * UserJurisdictionApplication用户权限申请
    * @param userid 用户id
    * @param type 0 代表申请恢复全部权限 1 代表申请恢复需求发布权限 2 代表申请恢复代取权限
    * @param applContent 用户申请理由
    * @param pic 用户申请附带的图片
    * @param status用户申请的状态
    * 用户申请需要管理员进行审核
    * Integer userid,Integer type,String applContent,String pic,String status
    * */

    @Override
    public TakeExpressResult UserJurisdictionApplication(UserJurisdictionApplicationValidator ujav, HttpServletRequest request){
        try{
            if (StringUtils.isBlank(ujav.getUserid()) || StringUtils.isBlank(ujav.getStatus()) || StringUtils.isBlank(ujav.getApplContent())){
                return TakeExpressResult.build(400,"请完善申请信息");
            }
            /*处理重复提交*/
            JurisdictionapplicationExample ja = new JurisdictionapplicationExample();
            ja.createCriteria().andUserIdEqualTo(Integer.parseInt(ujav.getUserid().trim()));
            List<Jurisdictionapplication> jas = jurisdictionapplicationMapper.selectByExample(ja);
            if (!jas.isEmpty()){
                for (int i = 0; i < jas.size();i++ ){
                    if (Integer.parseInt(ujav.getStatus()) == (jas.get(i).getStatus())){
                        return TakeExpressResult.build(400,"该权限已申请，请等待管理员的审核");
                    }
                }
            }
            UserExample userExample = new UserExample();
            UserExample.Criteria criteria = userExample.createCriteria();
            //实例化criteria
            criteria.andUserIdEqualTo(Integer.parseInt(ujav.getUserid()));
            List<User> userList = userMapper.selectByExample(userExample);
            //判断用户是否存在
            if (userList ==null || userList.isEmpty()){
                return TakeExpressResult.build(400,"用户不存在");
            }
            //获取用户的权限情况
            String status = userList.get(0).getStatus();
            /*
             *   0 代表申请恢复全部权限 1 代表申请恢复需求发布权限 2 代表申请恢复代取权限
             *   现在有几种情况，一种是用户权限处于良好的请况，再次申请该权限
             *   第二种就是权限被限制的情况申请权限
             * */

            if ((status.trim()).equals("0")){
                return TakeExpressResult.build(400,"当前你的权限没被限制");
            }

            /*
            * 当用户被限制权限与申请权限相同时提交申请,
            * 当用户当前状态是3，用户申请恢复状态 1 或者状态 2
            * 当用户状态是1 或者 2 时申请2或者1
            * */
            if (status.equals("1")){
                if ((ujav.getStatus()).equals("2")){
                    return TakeExpressResult.build(400,"申请恢复权限与被限制权限不符！");
                }
            }
            if (status.equals("2")){
                if ((ujav.getStatus()).equals("1")){
                    return TakeExpressResult.build(400,"申请恢复权限与被限制权限不符！");
                }
            }
            Jurisdictionapplication jurisdictionapplication = new Jurisdictionapplication();
            String pic = "";
            if (ujav.getUploadfile()==null || ujav.getUploadfile().isEmpty()){
                pic = "";
            }else {
                //将图片上传到服务器
                String path = request.getServletContext().getRealPath("static/user/application/picture/" + ujav.getUserid());
                Map<String, Object> map = pictureService.uploadPicture(ujav.getUploadfile(), path);
                System.out.println(map);
                if (map.get("status") == "0" || ((Integer) map.get("status")) == 0) {
                    pic = (String) map.get("url");
                }
            }
            jurisdictionapplication.setPic(pic);
            jurisdictionapplication.setUserId(Integer.parseInt(ujav.getUserid()));
            jurisdictionapplication.setStatus(Integer.parseInt(ujav.getStatus()));
            jurisdictionapplication.setUserAppContent(ujav.getApplContent());
            jurisdictionapplication.setConten("等待审核");
            //将用户申请提交到数据库表中
            jurisdictionapplicationMapper.insert(jurisdictionapplication);
            return TakeExpressResult.ok("请等待管理员审核");
        }catch (Exception e){
            System.out.println(e.getMessage());
            return TakeExpressResult.build(400,"申请权限异常");
        }
    }

    /*
    * auditUserJurisdictionApplicaton 审核用户权限申请
    * */

    @Override
    public TakeExpressResult auditUserJurisdictionApplicaton(Integer userid,String status,String content,Boolean check,HttpServletRequest request){
        /*
        * 0 代表申请恢复全部权限 1 代表申请恢复需求发布权限 2 代表申请恢复代取权限
        * user表中  用户状态：0-激活，1-禁止发布需求，2-禁止接单，3-冻结
        * */
        try{
            if (userid==null || status==null || StringUtils.isBlank(status)){
                return TakeExpressResult.build(400,"请求数据不完整！");
            }
            User user = userMapper.selectByPrimaryKey(userid);
            if (user==null){
                return TakeExpressResult.build(400,"该用户不存在");
            }else if (!user.getStatus().equals("3") && !status.equals("0") && !status.equals(user.getStatus()) ){
                System.out.println("测试");
                return TakeExpressResult.build(400,"当前用户已拥有此权限");
            }
            if (!check){
                EmailUtils.sendEmail(user.getEmail(),"","尊敬的"+user.getName()+"用户你好;你申请恢复权限尚未通过;原因为："+content);
                return TakeExpressResult.ok();
            }
            if (status.equals("1")){
                user.setStatus("2");
                user.setDemandScore(80);
            }else if (status.equals("2")){
                user.setStatus("1");
                user.setFetchScore(80);
            }else if (status.equals("0")){
                user.setStatus("0");
                user.setDemandScore(80);
                user.setFetchScore(80);
            }
            userMapper.updateByPrimaryKey(user);
            JurisdictionapplicationExample ju = new JurisdictionapplicationExample();
            ju.createCriteria().andUserIdEqualTo(Integer.parseInt(userid.toString())).andStatusEqualTo(Integer.parseInt(status));
            Jurisdictionapplication jurisdictionapplication = jurisdictionapplicationMapper.selectByExample(ju).get(0);
            if (jurisdictionapplication==null){
                return TakeExpressResult.build(400,"申请用户不存在");
            }
            if (StringUtils.isNotEmpty(jurisdictionapplication.getPic())){
                //删除用户申请上传的图片
                DelectPic.delect(request.getServletContext().getRealPath("")+jurisdictionapplication.getPic());
            }
            //删除表记录
            jurisdictionapplicationMapper.deleteByExample(ju);
            /*
            * 调用邮箱发送，提醒用户
            * */
            try{
                EmailUtils.sendEmail(user.getEmail(),"","尊敬的"+user.getName()+"用户你好;你申请恢复权限已通过;信誉是我们生存的基本准则，请让我们一起构建一片没有欺骗的乐土；欢迎你继续使用校园快递代取系统！期待下次为你服务！！！_=_");
                return TakeExpressResult.build(200,"已修改用户权限！");
            }catch (Exception e){
                return TakeExpressResult.build(500,"用户邮箱不存在");
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
            return TakeExpressResult.build(400,"修改用户权限异常！");
        }
    }

    /*
     * 管理员审核信息前将数据层数据返回到前端
     * @param page  当前页面显示的页码
     * @param rows  每页显示的记录数
     * */

    @Override
    public DataTableResult registerUser(int start, int length,String search) {
        //进行分页处理
        //初始化分页插件
        PageHelper.startPage(start/length+1,length);
        //执行查询
        RegisterExample registerExample = new RegisterExample();
        if (StringUtils.isNotEmpty(search)) {
            //执行查询
            registerExample.createCriteria().andLoginIdLike("%"+search+"%");
        }
        //去除重复的行
        registerExample.setDistinct(false);
        List<Register> list = registerMapper.selectByExample(registerExample);
        //取分页信息
        PageInfo<Register> pageInfo = new PageInfo<>(list);
        //返回处理分页信息
        DataTableResult result = new DataTableResult();
        result.setData(pageInfo.getList());
        RegisterExample registerExample1 = new RegisterExample();
        result.setRecordsTotal((int)registerMapper.countByExample(registerExample1));
        return result;
    }

    @Override
    public DataTableResult ScantionUser(int start, int length,String search) {
        //进行分页处理
        //初始化分页插件
        PageHelper.startPage(start / length + 1, length);
        UserExample userExample = new UserExample();
        //去除重复的行
        userExample.or().andDemandScoreLessThan(60);
        userExample.or().andFetchScoreLessThan(60);
        userExample.setDistinct(false);
        List<User> list = userMapper.selectByExample(userExample);
        List<User> list2 = new ArrayList<>();
        PageInfo<User> pageInfo;
        if (StringUtils.isNotEmpty(search)) {
            //执行查询
            //先判断userid在申请表中
            UserExample userExample2 = new UserExample();
            userExample2.createCriteria().andNameLike("%"+search+"%");
            List<User> userList = userMapper.selectByExample(userExample2);
            for (int i=0;i<userList.size();i++){
                for (int j=0;j<list.size();j++){
                    if ((userList.get(i).getUserId()).equals(list.get(j).getUserId())){
                        list2.add(userList.get(i));
                    }
                }
            }
            pageInfo = new PageInfo<>(list2);
        }else {
            pageInfo = new PageInfo<>(list);
        }
        //取分页信息,这里必须经过PageInfo初始化
        //返回处理分页信息
        DataTableResult result = new DataTableResult();
        result.setData(pageInfo.getList());
        result.setRecordsTotal(list.size());
        return result;
    }

    @Override
    public DataTableResult UserJurisdictionApplicationShow(int page, int rows,String search) {
        PageHelper.startPage(page/rows+1,rows);
        UserExample userExample = new UserExample();
        JurisdictionapplicationExample jurisdictionapplicationExample = new JurisdictionapplicationExample();
        //获取用户权限注册申请的记录表
        List<Jurisdictionapplication> jurisdictionapplications = jurisdictionapplicationMapper.selectByExample(jurisdictionapplicationExample);
        List<UserJurisdictionApplication> list = new ArrayList<>();
        List<User> list2 = new ArrayList<>();
        //获取搜索用户
        if (search != null){
            userExample.createCriteria().andNameLike("%"+search+"%");
            List<User> userList = userMapper.selectByExample(userExample);
            for (int i=0;i<jurisdictionapplications.size();i++){
                for (int j=0;j<userList.size();j++){
                    if ((jurisdictionapplications.get(i).getUserId()).equals(userList.get(j).getUserId())){
                        UserJurisdictionApplication application = new UserJurisdictionApplication(userList.get(j).getUserId(),userList.get(j).getName(),userList.get(j).getEmail(),
                                String.valueOf(jurisdictionapplications.get(i).getStatus()) ,userList.get(j).getDemandScore(),userList.get(j).getFetchScore(),
                                jurisdictionapplications.get(i).getUserAppContent(),jurisdictionapplications.get(i).getConten()
                        ,jurisdictionapplications.get(i).getPic(),userList.get(j).getStatus());
                        list.add(application);
                    }
                }
            }
        }else {
            for (Jurisdictionapplication a : jurisdictionapplications){
                User user1 = userMapper.selectByPrimaryKey(a.getUserId());
                UserJurisdictionApplication application  = new UserJurisdictionApplication(a.getUserId(),user1.getName(),user1.getEmail(),String.valueOf(a.getStatus()),user1.getDemandScore(),user1.getFetchScore(),a.getUserAppContent(),a.getConten(),a.getPic(),user1.getStatus());
                list.add(application);
            }
        }
        PageInfo<UserJurisdictionApplication> pageInfo = new PageInfo<>(list);
        //返回处理分页信息
        DataTableResult result = new DataTableResult();
        result.setData(pageInfo.getList());
        JurisdictionapplicationExample jurisdictionapplicationExample1 = new JurisdictionapplicationExample();
        result.setRecordsTotal((int) jurisdictionapplicationMapper.countByExample(jurisdictionapplicationExample1));
        return result;
    }
    @Override
    public Integer deleteApplicationUser(String id) {
        return jurisdictionapplicationMapper.deleteByPrimaryKey(Integer.parseInt(id.trim()));
    }
}
