package com.express.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * log
 * @author 
 */
@Data
public class Log implements Serializable {
    private Integer logId;

    private String logTime;

    /**
     * 记录ip（可以是ipv6）
     */
    private String ip;

    private String location;

    /**
     * 是否登录成功，0：失败，1：成功
     */
    private Boolean isSuccess;

    private static final long serialVersionUID = 1L;
}