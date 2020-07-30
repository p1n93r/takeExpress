package com.express.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * appeal
 * @author 
 */
@Data
public class Appeal implements Serializable {
    private Integer appealId;

    private Integer uid;

    /**
     * 申诉提交时间
     */
    private String appealTime;

    /**
     * 申诉的订单
     */
    private Integer orderId;

    /**
     * 申诉的理由
     */
    private String content;

    /**
     * 管理员处理的结果
     */
    private String dealResult;

    /**
     * 处理的时间
     */
    private String dealTime;

    private static final long serialVersionUID = 1L;
}