package com.express.service.impl;
import com.express.common.DataTableResult;
import com.express.domain.*;
import com.express.mapper.CommentMapper;
import com.express.mapper.DemandMapper;
import com.express.mapper.DemandOrderMapper;
import com.express.mapper.UserMapper;
import com.express.service.CommentService;
import com.express.utils.EmailUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/4/11 18:25
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DemandOrderMapper demandOrderMapper;
    @Autowired
    private DemandMapper demandMapper;
    @Override
    public int insertComment(Comment comment) {
        return commentMapper.insert(comment);
    }
    /*
    * 定义存放迭代找出的所有子代集合
    *
    * */

    private List<Comment> tempReplys = new ArrayList<>();
    /**
     * 根据parent_comment_id为“0”查询出所有父评论
     * 根据父评论的id查询出一级子回复
     * 根据子回复的id循环迭代查询出所有子集回复
     * */
    @Override
    public DataTableResult listComment(int page, int rows,User user) {
        //1.初始化分页插件
        PageHelper.startPage(page,rows);
        //2.执行查询,获取查询集合
        CommentExample commentExample = new CommentExample();
        /*
        * 根据历史订单，查找出所有与本用户的订单
        * 查询出本人代取的订单集合，这里主要遍历出作为代取方，需求方对自己的评论
        * */
        DemandOrderExample demandOrderExample = new DemandOrderExample();
        demandOrderExample.createCriteria().andUidEqualTo(user.getUserId());
        List<DemandOrder> demandOrders = demandOrderMapper.selectByExample(demandOrderExample);
        List<Comment> demandComments = new ArrayList<>();
        for (DemandOrder d : demandOrders){
            CommentExample commentE = new CommentExample();
            /*
            * 通过demandid可以找到需求发布者对自己的父级评论
            * 这里需要parent_comment_id的值不能0，不然就和下面查询出来的值重复
            * */

            commentE.createCriteria().andDemandIdEqualTo(d.getDemandId());
            if (commentMapper.selectByExample(commentE).get(0).getParentCommitId() == 0){
                demandComments.add(commentMapper.selectByExample(commentE).get(0));
            }
        }
        for (Comment c : demandComments){
            String parentNickname1 = c.getNickname();
            //根据父级节点的commentid查询出子评论
            CommentExample commentExample1 = new CommentExample();
            commentExample1.createCriteria().andParentCommitIdEqualTo(c.getCommentId());
            commentExample1.setDistinct(false);
            List<Comment> childComments = commentMapper.selectByExample(commentExample1);
            //查询出子评论
            queryChildComment(childComments,parentNickname1);
            c.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }

        /*
         * 查询本用户所有父级节点
         * 只显示与本人有关的评论,这里显示的是作为需求方主动评论的信息
         * */

        commentExample.createCriteria().andParentCommitIdEqualTo(0).andUserIdEqualTo(user.getUserId());
        commentExample.setDistinct(false);
        List<Comment> comments = commentMapper.selectByExample(commentExample);
        System.out.println("comments  :"+comments);
        for (Comment c : comments){
            String parentNickname1 = c.getNickname();
            //根据父级节点的commentid查询出子评论
            CommentExample commentExample1 = new CommentExample();
            commentExample1.createCriteria().andParentCommitIdEqualTo(c.getCommentId());
            commentExample1.setDistinct(false);
            List<Comment> childComments = commentMapper.selectByExample(commentExample1);
            //查询出子评论
            queryChildComment(childComments,parentNickname1);
            c.setReplyComments(tempReplys);
            tempReplys = new ArrayList<>();
        }
        /*将两者的评论合并，便于分页*/
        for (int i = 0;i < demandComments.size();i++){
            comments.add(demandComments.get(i));
        }
        //3.通过PageInfo的构造方法初始化分页信息
        PageInfo<Comment> pageInfo = new PageInfo<>(comments);
        DataTableResult result = new DataTableResult();
        //4.存储分页信息,前端通过该属性判断分页
        result.setPageInfo(pageInfo);
        result.setData(pageInfo.getList());
        //5.返回分页信息
        return result;
    }
    /*
    * 查询出子评论
    *
    * */

    private void queryChildComment(List<Comment> childComments,String parentNickname1){
        //判断是否有一级回复
        if (childComments.size()>0){
            //循环找出子评论的id
            for (Comment childComment:childComments){
                String parentNickname = childComment.getNickname();
                childComment.setParentNickname(parentNickname1);
                //添加子回复
                tempReplys.add(childComment);
                //查询二级以及所有子集回复
                recursively(childComment.getCommentId(), parentNickname);
            }
        }
    }
    /*
    * 查询一级评论一下的所有的评论
    * */

    private void recursively(Integer childId, String parentNickname1) {
        //根据子一级评论的id找到子二级评论
        CommentExample commentExample = new CommentExample();
        commentExample.createCriteria().andParentCommitIdEqualTo(childId);
        commentExample.setDistinct(false);
        List<Comment> replayComments = commentMapper.selectByExample(commentExample);
        if(replayComments.size() > 0){
            for(Comment replayComment : replayComments){
                String parentNickname = replayComment.getNickname();
                replayComment.setParentNickname(parentNickname1);
                Integer replayId = replayComment.getCommentId();
                tempReplys.add(replayComment);
                //循环迭代找出子集回复
                //利用递归的方式
                recursively(replayId,parentNickname);
            }
        }
    }

    /*
    * 实现系统判断用户信誉，扣除用户信誉积分
    * 采用aop的方式进行操着
    * */

    @Override
    public void updateUserFetchScore(Comment comment) {
        //执行代取用户信誉积分的扣除
        DemandOrderExample demandOrderExample = new DemandOrderExample();
        DemandOrderExample.Criteria criteria = demandOrderExample.createCriteria();
        criteria.andDemandIdEqualTo(comment.getDemandId());
        List<DemandOrder> demandOrders = demandOrderMapper.selectByExample(demandOrderExample);
        if (demandOrders.isEmpty()){
            return;
        }
        User user = userMapper.selectByPrimaryKey(demandOrders.get(0).getUid());
        /*失信一次扣除信誉积分五分*/
        int sort = user.getFetchScore()-5;
        user.setFetchScore(sort);
        userMapper.updateByPrimaryKey(user);
        try {
            EmailUtils.sendEmail(user.getEmail(),"失信惩罚","尊敬的同学你好;" +
                    "由于你在已完成的订单，单号为："+demandOrders.get(0).getOrderNumber()+" 中存在服务问题，系统将扣除你五分的信誉积分，信誉积分低于60将不能继续接单代取！");
        }catch (Exception e){
            System.out.println("用户邮箱不存在");
        }
        return;
    }
    @Override
    public void updateUserDemandSore(Comment comment) {
        /*扣除用户需求积分*/
        DemandExample demandExample = new DemandExample();
        demandExample.createCriteria().andDemandIdEqualTo(comment.getDemandId());
        List<Demand> demands = demandMapper.selectByExample(demandExample);
        if (demands.isEmpty()){
            return;
        }
        DemandOrderExample demandOrderExample = new DemandOrderExample();
        DemandOrderExample.Criteria criteria = demandOrderExample.createCriteria();
        criteria.andDemandIdEqualTo(comment.getDemandId());
        List<DemandOrder> demandOrders = demandOrderMapper.selectByExample(demandOrderExample);
        if (demandOrders.isEmpty()){
            return;
        }
        User user  = userMapper.selectByPrimaryKey(demands.get(0).getUid());
        /*失信一次扣除信誉积分五分*/
        user.setDemandScore(user.getDemandScore()-5);
        userMapper.updateByPrimaryKey(user);
        try {
            EmailUtils.sendEmail(user.getEmail(),"失信惩罚","尊敬的同学你好;" +
                    "由于你在已完成的订单，单号为："+demandOrders.get(0).getOrderNumber()+" 中存在描述不符问题，系统将扣除你五分的信誉积分，信誉积分低于60将不能继续发布需求！");
        }catch (Exception e){
            System.out.println("用户邮箱不存在");
        }
        return;
    }

    @Override
    public int deleteComment(int commentid) {
        return commentMapper.deleteByPrimaryKey(commentid);
    }
}
