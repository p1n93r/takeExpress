package com.express.service.user;

import com.express.domain.Feedback;

import java.util.List;

/**
 * @author cassie
 */
public interface FeedbackService {

    /**
     * 1、添加一条feedback记录（已注册用户）
     * @param feedback
     * @return
     * @throws Exception
     */
    public int insertFeedback(Feedback feedback)throws Exception;


    /**
     * 2、管理员查看用户反馈的记录
     * @return
     * @throws Exception
     */
    public String selectAdminFeedback()throws Exception;

    /**
     * 3、管理员通过fid删除一条feedback记录
     * @param fid
     * @return
     * @throws Exception
     */
    public boolean deleteOneFeedback(Integer fid)throws Exception;


    /**
     * 4、管理员通过fid找到一条feedback记录并回复反馈信息（修改feedback中的status和reply）
     * @param fid
     * @return
     * @throws Exception
     */
    public boolean updateOneFeedback(Integer fid,String reply)throws Exception;



}
