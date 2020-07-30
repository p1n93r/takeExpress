package com.express.utils.Validator;

import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 创建验证类
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/18 10:40
 */

@Data
public class RegisterValidator {
    @Size(min=6, max=8,message = "请输入6到8位账号")
    private String loginid;
    @Size(min=6 ,message = "请输入至少6位密码")
    private String password;
    @Size(min=6, max=8,message = "两次输入的密码不一致")
    private String passwordcheck;
    @Email(message="请输入正确的邮箱(邮箱格式不正确)")
    private String email;
    @Size(min = 11,max = 11,message = "请输入正确格式的手机号")
    private String phone;
    @Size(min = 4,max = 4,message = "请输入4位验证码")
    private String code;
    @NotNull(message = "上传学生证不能为空")
    private MultipartFile stupic;

}
