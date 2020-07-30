package com.express.aspect;
import com.express.common.CollectionBean;
import com.express.domain.*;
import com.express.mapper.DemandMapper;
import com.express.mapper.DemandOrderMapper;
import com.express.service.CommentService;
import com.express.utils.KeyWordUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/4/12 14:07
 */
@Aspect
@Slf4j
@Component
public class CommentAspect {
    @Autowired
    private CommentService commentService;
    @Autowired
    private DemandMapper demandMapper;
    @Autowired
    private CollectionBean collectionBean;
    @Autowired
    private DemandOrderMapper demandOrderMapper;

    @Pointcut(value = "execution(* com.express.service.CommentService.insertComment(..))")
    private void pointcut(){}
    @After("pointcut()")
    public void after(JoinPoint joinPoint){
        //判断是否是需求发布者和代取双方之间的评论，如果是就判断评论内容，是否包含一些特殊词汇，如不符合描述
        //只考虑需求方对代取方的评论
        //判断评论用户是否是需求发布者
        Object[] args = joinPoint.getArgs();
        Comment comment = (Comment) args[0];
        DemandExample demandExample = new DemandExample();
        DemandExample.Criteria criteria = demandExample.createCriteria();
        if (comment.getDemandId()==null){
            return;
        }
        criteria.andDemandIdEqualTo(comment.getDemandId());
        List<Demand> demands = demandMapper.selectByExample(demandExample);
        //防止空指针调用
        if (demands.isEmpty()) {
            return;
        }
        /*需求用户对代取用户进行评论*/
        if (demands.get(0).getUid().equals(comment.getUserId()) && comment.getParentCommitId()==0){
            Set<String> wordSet = collectionBean.getSets();
            int wordLength = KeyWordUtil.checkWord(KeyWordUtil.handleToMap(wordSet),comment.getContent().trim(), 0);
            if (wordLength != 0 && comment.getScore() < 80){
                //执行代取用户的信誉积分扣除
                commentService.updateUserFetchScore(comment);
                return;
            }
        }
        return;
    }
    @After("pointcut()")
    public void demandAfter(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        Comment comment = (Comment) args[0];
        DemandOrderExample demandOrderExample = new DemandOrderExample();
        demandOrderExample.createCriteria().andDemandIdEqualTo(comment.getDemandId()).andUidEqualTo(comment.getUserId());
        List<DemandOrder> demandOrders = demandOrderMapper.selectByExample(demandOrderExample);
        if (demandOrders.isEmpty()){
            return;
        }
        /*
        *代取用户对需求放描述的评价
        * 这里说明一下，，我设计的comment数据表当parent_comment_id为0时，代表的是需求方对代取方的评论
        * 为-1时表示代取方对需求方的评论
        * */
        if (demandOrders.get(0).getUid().equals(comment.getUserId()) && comment.getParentCommitId()==-1){
            Set<String> wordSet = collectionBean.getSets();
            int wordLength = KeyWordUtil.checkWord(KeyWordUtil.handleToMap(wordSet),comment.getContent().trim(), 0);
            if (wordLength != 0 && comment.getScore() < 80){
                //执行代取用户的信誉积分扣除
                commentService.updateUserDemandSore(comment);
                return;
            }
        }
    }

}
