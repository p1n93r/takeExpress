package com.express.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * comment
 * @author 
 */
@Data
public class Comment implements Serializable {
    /**
     * 评论id
     */
    private Integer commentId;

    /**
     * 用户评论的id
     */
    private Integer userId;

    /**
     * 父级评论id，0代表需求方给代取方评论，-1代表代取方给需求方评论,其他代表回复评论
     */
    private Integer parentCommitId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private String time;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 评分
     */
    private Integer score;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 评论的需求订单号
     */
    private Integer demandId;
    /*
     * 回复评论
     * 使用递归的方式
     * */

    private List<Comment> replyComments = new ArrayList<>();
    private Comment parentComment;
    private String parentNickname;
    private static final long serialVersionUID = 1L;
}