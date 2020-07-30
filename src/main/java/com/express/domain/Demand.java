package com.express.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * demand
 * @author 
 */
@Data
public class Demand implements Serializable {
    private Integer demandId;

    private Integer uid;

    /**
     * 参考价格（需求方支付给系统的钱）
     */
    private Double price;

    /**
     * 状态：0：需求已发布， 1、代取人已接单  2、快递已取出3：代取员即将出发   4：代取员到达目的地，5：订单完成，6：快递退回，5小时内自取不收费
     */
    private String status;

    /**
     * 需求描述
     */
    private String description;

    private static final long serialVersionUID = 1L;
}