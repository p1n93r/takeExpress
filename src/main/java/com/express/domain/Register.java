package com.express.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * register
 * @author 
 */
@Data
public class Register implements Serializable {
    public Register(){}
    public Register(String stuPic, String content, String loginId, String loginPwd, String phone, String email, String status) {
        this.stuPic = stuPic;
        this.content = content;
        this.loginId = loginId;
        this.loginPwd = loginPwd;
        this.phone = phone;
        this.email = email;
        this.status = status;
    }
    private Integer registerId;

    /**
     * 学生证图片
     */
    private String stuPic;

    /**
     * 管理员审核注册反馈信息
     */
    private String content;

    /**
     * 登录账号
     */
    private String loginId;

    /**
     * 登录密码
     */
    private String loginPwd;

    /**
     * 通知电话
     */
    private String phone;

    private String email;

    /**
     * 状态：0-成功，1-失败
     */
    private String status;

    private static final long serialVersionUID = 1L;
}