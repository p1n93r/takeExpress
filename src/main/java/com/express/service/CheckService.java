package com.express.service;

public interface CheckService {
    //当输入手机号时先验证手机号是否存在
    boolean checkPhone(String phone);
    //用于登录验证
    boolean checkManagerPhone(String phone);
    //用于修改绑定验证
    boolean checkManagerBindPhone(String phone);
    //用于用户登陆验证手机号是否存在
    boolean checkUserPhone(String phone);
    boolean checkLoginId(String loginid);
    boolean checkEmail(String email);
}
