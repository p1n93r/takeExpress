package com.express.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    private Integer userId;

    /**
     * 真实姓名
     */
    private String name;

    private String sex;

    private String email;

    private String phone;

    /**
     * 用户的头像
     */
    private String pic;

    /**
     * 学生证的图片
     */
    private String stuPic;

    /**
     * 用于人脸识别的头像
     */
    private String headPic;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用于支付给用户的钱的支付宝账号
     */
    private String alipay;

    /**
     * 用户状态：0-激活，1-禁止发布需求，2-禁止接单，3-冻结
     */
    private String status;

    /**
     * 需求积分
     */
    private Integer demandScore;

    /**
     * 代取积分
     */
    private Integer fetchScore;
    public User(){}

    private static final long serialVersionUID = 1L;
}