package com.express.domain;

import java.io.Serializable;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * feedback
 * @author
 */
@Data
public class Feedback implements Serializable {
    private Integer fid;

    private String createTime;

    private Integer uid;

    @NotBlank
    private String content;

    /**
     * 状态：0-待处理，1-已处理
     */
    private String status;

    /**
     * 管理员回复
     */
    private String reply;

    /**
     * 用户反馈的主题
     */
    @NotBlank
    private String subject;

    /**
     * 用户接收reply
     */
    @Email
    @NotBlank
    private String email;

    private static final long serialVersionUID = 1L;
}
