package com.express.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * log_operation
 * @author 
 */
@Data
public class LogOperation implements Serializable {
    /**
     * 日志操作表id
     */
    private Integer logOpid;

    /**
     * 日志表操作类型：增加、修改、删除
     */
    private String logOptype;

    /**
     * 日志操作记录表的日志记录主内容
     */
    private String logOpcontent;

    private String logOptime;

    private static final long serialVersionUID = 1L;
}