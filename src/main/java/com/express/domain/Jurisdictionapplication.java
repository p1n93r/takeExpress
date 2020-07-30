package com.express.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * jurisdictionapplication
 * @author 
 */
@Data
public class Jurisdictionapplication implements Serializable {
    /**
     * 自增id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 0 代表申请恢复全部权限 1 代表申请恢复需求发布权限 2 代表申请恢复代取权限
     */
    private Integer status;

    /**
     * 反馈用户申请信息
     */
    private String conten;

    /**
     * 用户申请理由
     */
    private String userAppContent;

    /**
     * 用户申请理由附带的图片
     */
    private String pic;

    private static final long serialVersionUID = 1L;
}