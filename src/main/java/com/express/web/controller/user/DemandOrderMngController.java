package com.express.web.controller.user;
import com.express.service.user.DemandOrderMngService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * @author p1n93r
 */
@RestController
public class DemandOrderMngController extends UserBaseController {

    @Resource
    DemandOrderMngService demandOrderMngService;

    /**
     * @return 需求方订单数据：JSON格式
     */
    @RequestMapping(value = "/getDemandOrder",produces = "application/json;charset=utf-8")
    public String getDemandOrder() throws Exception {
        String result = demandOrderMngService.findDemandOrder(userlogin.getUserId());
        return result;
    }



}
