package com.express.web.controller.user;

import com.express.common.DataTableResult;
import com.express.common.TakeExpressResult;
import com.express.domain.Comment;
import com.express.domain.User;
import com.express.mapper.UserMapper;
import com.express.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author fyzn12
 * @version 1.0
 * @date 2020/4/12 13:51
 */
@Controller
public class CommentController extends UserBaseController{
    @Autowired
    private CommentService commentService;
    @RequestMapping(value = "/comment",produces = "application/json;charset=utf-8")
    @ResponseBody
    public TakeExpressResult comment(Comment comment, HttpServletRequest request){
        comment.setUserId(userlogin.getUserId());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = format.format(new Date());
        comment.setTime(time);
        comment.setAvatar(user.getPic());
        comment.setNickname(user.getNickname());
        int i = commentService.insertComment(comment);
        if (i != 0){
            return TakeExpressResult.ok();
        }
        return TakeExpressResult.build(400,"评论失败");
    }

    /*
    * 显示评论
    * */

    @RequestMapping(value = "/aa")
    public String getComment(Model model){
        DataTableResult dataTableResult = commentService.listComment(1,12,user);
        model.addAttribute("comments",dataTableResult.getData());
            /*
            * 为了前端能显示当前评论回复者的头像，必须将当前用户的头像地址传回前端
            * */
         model.addAttribute("pic",user.getPic());
         model.addAttribute("nickname",user.getNickname());
         model.addAttribute("userid",user.getUserId());

        return "user/comment";
    }
}
