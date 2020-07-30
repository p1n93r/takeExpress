package com.express.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * appeal_pic
 * @author 
 */
@Data
public class AppealPic implements Serializable {
    private Integer appealPicId;

    /**
     * 申诉图片路径
     */
    private String picLocation;

    private static final long serialVersionUID = 1L;
}