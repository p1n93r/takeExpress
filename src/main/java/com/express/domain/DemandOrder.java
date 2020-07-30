package com.express.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * demand_order
 * @author 
 */
@Data
public class DemandOrder implements Serializable {
    /**
     * 订单的自增检索id
     */
    private Integer orderId;

    /**
     * 订单编号
     */
    private String orderNumber;

    /**
     * 订单创建时间（需求被接单后创建）
     */
    private String createTime;

    /**
     * 结算给代取方的钱
     */
    private Double price;

    /**
     * 参考的需求的id
     */
    private Integer demandId;

    /**
     * 订单取消时间
     */
    private String returnTime;

    /**
     * 代取方的user_id
     */
    private Integer uid;

    private static final long serialVersionUID = 1L;
}