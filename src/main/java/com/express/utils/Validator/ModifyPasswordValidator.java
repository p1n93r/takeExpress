package com.express.utils.Validator;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/19 10:16
 */
@Data
public class ModifyPasswordValidator {
    //这里不能直接在managerpojo内直接加注解
    @NotNull(message = "用户id不能为空")
    private String loginid;
    @NotNull(message = "请输入至少6位的密码")
    @Size(min = 6,message = "请输入至少6位的密码")
    private String password;
    @NotNull(message = "请确定密码")
    private String passwordcheck;

    @Length(min = 11,max = 11,message = "请输入11位的电话号码")
    private String phone;
    @Length(min = 4,max = 4,message = "请输入4位验证码")
    private String code;
}
