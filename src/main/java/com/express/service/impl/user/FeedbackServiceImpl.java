package com.express.service.impl.user;

import com.express.domain.Feedback;
import com.express.domain.FeedbackExample;
import com.express.domain.User;
import com.express.domain.UserLogin;
import com.express.mapper.FeedbackMapper;

import com.express.mapper.UserLoginMapper;
import com.express.mapper.UserMapper;
import com.express.service.user.FeedbackService;
import com.express.utils.EmailUtils;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * @author Cassie
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Resource
    FeedbackMapper feedbackMapper;

    @Resource
    UserLoginMapper userLoginMapper;


    /**
     * 1、添加一条feedback记录（已注册用户）
     * @param feedback
     * @return
     * @throws Exception
     */

    @Override
    public int insertFeedback(Feedback feedback) throws Exception {
        //传入到此service里的feedback对象，此feedback对象已经设置好了属性（uid、creatTime等）
        //无需在此方法内设置
        int i = feedbackMapper.insertSelective(feedback);
        if(i>0){
            EmailUtils.sendEmail(feedback.getEmail(),"管理员回复","反馈内容已收到，感谢您的反馈，管理员正在处理~~");
        }
        return i;
    }


    /**
     * 2、管理员查看所有用户的反馈
     * @return
     * @throws Exception
     */

    @Override
    public String selectAdminFeedback() throws Exception {
        FeedbackExample feedbackExample = new FeedbackExample();
        feedbackExample.setOrderByClause("fid");
        List<Feedback> feedbacks = feedbackMapper.selectByExample(feedbackExample);
        JSONArray jsonArray = new JSONArray();
        for(Feedback v:feedbacks){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fid",v.getFid());
            jsonObject.put("createTime",v.getCreateTime());
            UserLogin userLogin = userLoginMapper.selectByPrimaryKey(v.getUid());
            jsonObject.put("loginId",userLogin.getLoginId());
            jsonObject.put("content",v.getContent());
            jsonObject.put("status",v.getStatus());
            jsonObject.put("reply",v.getReply());
            jsonObject.put("subject",v.getSubject());
            jsonObject.put("email",v.getEmail());
            jsonArray.add(jsonObject);
        }
        JSONObject res = new JSONObject();
        res.put("data",jsonArray);
        return res.toString();
    }

    /**
     * 3、管理员根据fid删除一条feedback记录
     * @param fid
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteOneFeedback(Integer fid) throws Exception {
        int i = feedbackMapper.deleteByPrimaryKey(fid);
        if(i>0){
            return true;
        }
        return false;
    }

    /**
     *  4、管理员通过fid找到一条feedback记录并回复反馈信息（修改feedback中的status和reply）
     * @param fid
     * @return
     * @throws Exception
     */
    @Override
    public boolean updateOneFeedback(Integer fid,String reply) throws Exception {
        Feedback feedback = feedbackMapper.selectByPrimaryKey(fid);
        feedback.setStatus("1");
        //设置回复是由前端页面传过来的回复内容
        feedback.setReply(reply);
        int i = feedbackMapper.updateByPrimaryKey(feedback);
        if(i>0) {
            UserLogin userLogin = userLoginMapper.selectByPrimaryKey(feedback.getUid());
            EmailUtils.sendEmail(feedback.getEmail(), "管理员处理结果回复", userLogin.getLoginId() + "您好，我们已经对您的反馈信息进行了处理，以下是处理结果：" + feedback.getReply());
            return true;
        }
        return false;
    }


}
