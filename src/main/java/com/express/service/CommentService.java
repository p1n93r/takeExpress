package com.express.service;

import com.express.common.DataTableResult;
import com.express.domain.Comment;
import com.express.domain.User;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/4/11 17:35
 */
public interface CommentService {
    int insertComment(Comment comment);
    /*
     * 显示用户评论
     * @param page  当前页面显示的页码
     * @param rows  每页显示的记录数
     *
    */

    DataTableResult listComment(int page, int rows,User user);
    /*
    * 利用aop判断用户评论的信息，计算用户的信誉积分
    *
    * */

    void updateUserFetchScore(Comment comment);
    void updateUserDemandSore(Comment comment);

    /*
    * 管理员删除评论
    * */

    int deleteComment(int commentid);



}
