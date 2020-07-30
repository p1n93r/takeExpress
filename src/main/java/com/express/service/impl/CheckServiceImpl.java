package com.express.service.impl;
import com.express.domain.*;
import com.express.mapper.ManagerMapper;
import com.express.mapper.RegisterMapper;
import com.express.mapper.UserLoginMapper;
import com.express.mapper.UserMapper;
import com.express.service.CheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;


@Service

public class CheckServiceImpl implements CheckService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserLoginMapper userLoginMapper;
    @Autowired
    private RegisterMapper registerMapper;
    @Autowired
    private ManagerMapper managerMapper;
    @Override
    public boolean checkPhone(String phone) {
        RegisterExample registerExample = new RegisterExample();
        RegisterExample.Criteria criteria1 = registerExample.createCriteria();
        criteria1.andPhoneEqualTo(phone.trim());
        List<List> info = new ArrayList<>();
        try{
            info.add(registerMapper.selectByExample(registerExample));
        }catch (Exception e){
            System.out.println("执行异常"+e.getMessage());
        }
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(phone.trim());
        try{
            info.add(userMapper.selectByExample(userExample));
        }catch (Exception e){
            System.out.println("执行异常"+e.getMessage());
        }
        ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria criteria2 = managerExample.createCriteria();
        criteria2.andPhoneEqualTo(phone.trim());
        info.add(managerMapper.selectByExample(managerExample));
        if (info.get(0).isEmpty() && info.get(1).isEmpty() && info.get(2).isEmpty()){
            return false;
        }
        return true;
    }
    /**
     * 管理员登陆验证
     * */
    @Override
    public boolean checkManagerPhone(String phone){
        ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria criteria = managerExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<Manager> managerList = managerMapper.selectByExample(managerExample);
        if (managerList.isEmpty()){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkManagerBindPhone(String phone) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<User> userList = userMapper.selectByExample(userExample);
        ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria criteria2 = managerExample.createCriteria();
        criteria2.andPhoneEqualTo(phone);
        List<Manager> managerList = managerMapper.selectByExample(managerExample);
        if (managerList.isEmpty() && userList.isEmpty()){
            return true;
        }
        return false;
    }
    /*
    * 用户短信登陆时验证号码
    * */

    @Override
    public boolean checkUserPhone(String phone) {
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andPhoneEqualTo(phone);
        List<User> managerList = userMapper.selectByExample(userExample);
        if (managerList.isEmpty()){
            return false;
        }
        return true;
    }
    @Override
    public boolean checkLoginId(String loginid) {
        RegisterExample registerExample = new RegisterExample();
        RegisterExample.Criteria criteria1 = registerExample.createCriteria();
        criteria1.andLoginIdEqualTo(loginid.trim());
        List<Register> registers = new ArrayList<>();
        try{
            registers = registerMapper.selectByExample(registerExample);
        }catch (Exception e){
            System.out.println("执行异常"+e.getMessage());
        }
        UserLoginExample userLoginExample = new UserLoginExample();
        UserLoginExample.Criteria criteria = userLoginExample.createCriteria();
        criteria.andLoginIdEqualTo(loginid);
        List<UserLogin> userLogin = userLoginMapper.selectByExample(userLoginExample);
        if (userLogin == null || userLogin.isEmpty() && registers ==null || registers.isEmpty()){
            return true;
        }
        return false;
    }

    @Override
    public boolean checkEmail(String email) {
        RegisterExample registerExample = new RegisterExample();
        RegisterExample.Criteria criteria1 = registerExample.createCriteria();
        criteria1.andEmailEqualTo(email.trim());
        List<List> test = new ArrayList<>();
        //Map<Object,Object> info = new HashMap<>();
        try{
            test.add(registerMapper.selectByExample(registerExample));
        }catch (Exception e){
            System.out.println("执行异常"+e.getMessage());
        }
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andEmailEqualTo(email.trim());
        try{
            test.add(userMapper.selectByExample(userExample));
        }catch (Exception e){
            System.out.println("执行异常"+e.getMessage());
        }
        ManagerExample managerExample = new ManagerExample();
        ManagerExample.Criteria criteria2 = managerExample.createCriteria();
        criteria2.andEmailEqualTo(email.trim());
        test.add(managerMapper.selectByExample(managerExample));
        if (test.get(0).isEmpty() && test.get(1).isEmpty() && test.get(2).isEmpty()){
            return true;
        }
        return false;
    }
}
