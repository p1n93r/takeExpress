package com.express.common;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 用于接收人脸识别错误信息
 * @author fyzn12
 * @version 1.0
 * @date 2020/3/22 19:28
 */
@Data
public class faceErroeResult {

    public faceErroeResult(){}
    private String error_code,error_msg,log_id,timestamp,cached;
    private Map<Object,Object> result;
}
