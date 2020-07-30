package com.express.common;

import com.github.pagehelper.PageInfo;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/4/12 18:49
 */
@Data
public class CommentResult {
    private int code;
    private List<?> rows;
    private Map<Object,Object> data;
    private PageInfo<?> pageInfo;
}
