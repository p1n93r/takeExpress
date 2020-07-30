package com.express.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/4/16 18:49
 */
@Data
public class UserJurisdictionApplication implements Serializable {

    private Integer userId;

    /**
     * 真实姓名
     */
    private String name;

    private String email;

    /**
     * 用户状态：0-激活，1-禁止发布需求，2-禁止接单，3-冻结
     */
    private String status;
    /*
    * 用户申请权限码
    * */
    private String applicationStatus;

    /**
     * 需求积分
     */
    private Integer demandScore;

    /**
     * 代取积分
     */
    private Integer fetchScore;
    /*
    * 申请理由
    * */
    private String userAppContent;
    /*
    * 反馈用户申请信息
    */

    private String conten;

    private String pic;

    public UserJurisdictionApplication(Integer userId, String name, String email, String applicationStatus, Integer demandScore, Integer fetchScore, String userAppContent, String conten, String pic,String status) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.applicationStatus = applicationStatus;
        this.demandScore = demandScore;
        this.fetchScore = fetchScore;
        this.userAppContent = userAppContent;
        this.conten = conten;
        this.pic = pic;
        this.status = status;
    }
}
