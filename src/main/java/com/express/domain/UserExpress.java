package com.express.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * user_express
 * @author 
 */
@Data
public class UserExpress implements Serializable {
    private Integer expressId;

    private Integer uid;

    /**
     * 待查询快递的物流公司
     */
    private String company;

    /**
     * 待查询快递的物流单号
     */
    private String num;

    private static final long serialVersionUID = 1L;
}