package com.express.utils.Validator;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/19 19:36
 */
@Data
public class ModifyPhoneValidator {
    @NotNull(message = "管理员id不能为空")
    private String managerid;
    @NotNull(message = "请输入11位的电话号码")
    @Length(min = 11,max = 11,message = "请输入11位的电话号码")
    private String phone;
    @NotNull(message = "请输入4位验证码")
    @Length(min = 4,max = 4,message = "请输入4位验证码")
    private String code;
}
