package com.express.utils.Validator;

import lombok.Data;
import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/19 20:51
 */
@Data
public class ModifyEmailValidator {
    @NotNull
    private String loginid;
    @Email
    private String email;
}
