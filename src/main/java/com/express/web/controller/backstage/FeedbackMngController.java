package com.express.web.controller.backstage;

import com.express.service.user.FeedbackService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Cassie
 */
@RestController
public class FeedbackMngController extends ManagerBaseController {
    @Autowired
    FeedbackService service;

    /**
     *  2、管理员查看所有feedback记录
     * @return
     */
    @RequestMapping(value = "/findFeedback",produces = "application/json;charset=utf-8")
    public String findFeedbackAdmin(){
        try {
            String s = service.selectAdminFeedback();
            return s;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 3、管理员删除一条feedback记录
     * @return
     */
    @RequestMapping(value = "/deleteOneFeedback",produces = "application/json;charset=utf-8")
    public String adminDeleteFeedback(@RequestParam Integer fid){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",false);
        try {
            boolean b = service.deleteOneFeedback(fid);
            jsonObject.put("result",b);
            return jsonObject.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }



    /**
     * 4、管理员进行feedback回复
     * @param fid feedback的fid
     * @param reply 管理员的回复信息
     * @return 代表是否修改成
     */
    @RequestMapping(value = "/updateFeedback",produces = "application/json;charset=utf-8")
    public String handleFeedback(@RequestParam Integer fid,@RequestParam String reply){
        System.out.println("fid："+fid+"====reply:"+reply);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result",false);
        if(fid!=null&&!"".equals(reply)){
            try {
                service.updateOneFeedback(fid,reply);
                jsonObject.put("result",true);
                return jsonObject.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonObject.toString();
    }

}
