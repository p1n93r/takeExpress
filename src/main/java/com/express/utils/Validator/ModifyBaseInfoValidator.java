package com.express.utils.Validator;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/23 13:34
 */
@Data
public class ModifyBaseInfoValidator {
    @NotNull(message = "请先登录再进行修改")
    private String loginid;
    private String nickname,sex,content;
    @Email(message = "请输入正确的邮箱格式")
    private String email;
    private MultipartFile handpic;
}
