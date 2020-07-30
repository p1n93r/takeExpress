package com.express.utils.Validator;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/22 18:53
 */
@Data
public class PhoneloginValidator {
    @NotNull
    @Length(min = 11,max = 11,message = "请输入11位的电话号码")
    private String phone;
    @NotNull
    @Length(min = 4,max = 4,message = "请输入4位验证码")
    private String code;
}
