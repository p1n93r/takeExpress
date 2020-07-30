package com.express.utils.Validator;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/20 18:17
 */
@Data
public class LoginValidator {
    //方便管理员登录和用户登录验证
    @Length(min = 6,max = 8,message = "请输入6到8位的登录账号")
    private String id;
    @Length(min = 6,message = "请输入至少6位登录密码")
    private String password;
}
