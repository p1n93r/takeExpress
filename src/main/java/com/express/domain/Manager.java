package com.express.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * manager
 * @author 
 */
@Data
public class Manager implements Serializable {
    public Manager(){}
    private Integer managerId;

    private String nickname;

    private String sex;

    /**
     * 个性签名
     */
    private String signature;

    /**
     * 登录id
     */
    private String loginId;

    private String pic;

    /**
     * 登录密码
     */

    private String loginPwd;

    /**
     * 人脸识别头像
     */
    private String headPic;

    private String phone;

    private String email;


    private static final long serialVersionUID = 1L;
}