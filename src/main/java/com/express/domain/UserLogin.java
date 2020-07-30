package com.express.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * user_login
 * @author 
 */
@Data
public class UserLogin implements Serializable {
    /**
     * 参照user表的user_id
     */
    private Integer userId;

    /**
     * 用户登录ID
     */
    private String loginId;

    /**
     * 用户登录密码
     */
    private String loginPwd;

    private static final long serialVersionUID = 1L;
}