package com.express.web.controller.test;


import com.express.utils.alipay.PayOnPc;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.UUID;

/**
 * 用于测试访问的控制器
 * @author Cassie
 */
@Controller
public class TestController {



    /**
     * 一个模拟支付生成订单的控制器
     * @param money 支付金额
     * @return 返回的json有两个键：html、num；html：前端将此界面显示出来即可；num：本次交易的订单号，需要保证此订单号全站唯一
     */
    @RequestMapping(value = "/alipayTest",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String alipayTest(String money){
        //http://localhost:8080/takeExpress_war_exploded/alipayTest
        //Notice:这只是一个demo，没有做订单业务处理，你需要生成此订单，保存到数据库，支付完成后改变此订单的状态

        JSONObject res = PayOnPc.pay("把笨笨买了", UUID.randomUUID().toString(), money);
        return res.toString();
    }


    /**
     * 提供给前端ajax进行订单查询的接口
     * @param num 商户订单号
     * @return 返回个json，根据此json的code=10000以及msg=Success来判断是否支付成功
     */
    @RequestMapping(value = "/alipayQuery",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String alipayQuery(String num){
        JSONObject query = PayOnPc.query(num);
        //如果主动查询订单，发现订单未支付，需要关闭此次支付订单
        if(!"10000".equals(query.get("code"))){
            Boolean res = PayOnPc.cancelPay(num);
            if(res){
                query.put("msg","订单已取消");
            }
        }
        return query.toString();
    }

    @RequestMapping("/testSaft")
    public String getTest(){
        return "backstage/managersafe";
    }

    /**
     * 对订单进行退款，退款金额必须与订单付款金额一致
     * @param num 订单号
     * @param money 金额
     * @return 一个代表是否成功的json，result=true代表退款成功
     */
    @RequestMapping(value = "/alipayRefund",produces = "application/json;charset=utf-8")
    @ResponseBody
    public String alipayRefund(String num,String money){
        JSONObject res = new JSONObject();
        res.put("result",false);
        Boolean refundRes = PayOnPc.refund(num, money);
        if(refundRes){
            res.put("result",true);
            //当然，退款成功，需要进行本系统的业务逻辑处理，比如销毁本系统的此笔订单（建议保留数据做统计，仅仅改变其状态即可）
        }
        return res.toString();
    }




}
