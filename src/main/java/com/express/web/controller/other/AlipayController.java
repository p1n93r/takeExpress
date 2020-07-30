package com.express.web.controller.other;

import com.alipay.easysdk.factory.Factory;
import com.express.utils.EmailUtils;
import com.express.utils.alipay.GetConfig;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝支付成功后的的同步以及异步回调
 * @author P1n93r
 */
@RestController
@RequestMapping("/alipay")
public class AlipayController {


    /**
     * 初始化Factory
     */
    @ModelAttribute
    public void initFactory(){
        Factory.setOptions(GetConfig.get());
    }


    /**
     * 异步回调
     * @param request 支付宝的请求
     * @return 是否支付成功
     * @throws Exception 异常
     */
    @RequestMapping("/notify")
    public String notify(HttpServletRequest request) throws Exception {

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (String name : requestParams.keySet()) {
            String[] values = requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }

        //异步签名验证
        boolean signVerified = Factory.Payment.Common().verifyNotify(params);

        EmailUtils.sendEmail("1725367974@qq.com","异步回调页面",params.toString());


        //——请在这里编写您的程序（以下代码仅作参考）——

        /* 实际验证过程建议商户务必添加以下校验：
        1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
        2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
        3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
        4、验证app_id是否为该商户本身。
        */
        //签名验证成功
        if(signVerified) {
            /**
             * 支付宝POST过来的数据如下：
             * {
             * gmt_create=2020-04-26 08:29:18,
             * charset=UTF-8,
             * gmt_payment=2020-04-26 08:29:30,
             * notify_time=2020-04-26 08:29:31,
             * subject=把笨笨买了,
             * buyer_id=2088102180767730,
             * invoice_amount=99.00,
             * version=1.0,
             * notify_id=2020042600222082930067730506409966,
             * fund_bill_list=[{"amount":"99.00","fundChannel":"ALIPAYACCOUNT"}],
             * notify_type=trade_status_sync,
             * out_trade_no=c49eab8d-dc43-4477-b17c-6e9d94c32719,       //我们系统中的订单号，需要验证此订单号的状态是否是已完成支付
             * total_amount=99.00,      //验证对应订单的金额是不是这个数，防止攻击者支付几毛钱然后传过来的钱是99999元
             * trade_status=TRADE_SUCCESS,      //验证此单交易是否支付成功
             * trade_no=2020042622001467730501165863,
             * auth_app_id=2016102400753062,
             * receipt_amount=99.00,
             * point_amount=0.00,
             * app_id=2016102400753062,     //验证此app_id是不是咱们这个系统的商家id，这个我会告诉你怎么获取
             * buyer_pay_amount=99.00,
             * seller_id=2088102180921046
             * }
             */



            //商户订单号
            String out_trade_no = request.getParameter("out_trade_no");

            //支付宝交易号
            String trade_no = request.getParameter("trade_no");

            //交易状态
            String trade_status = request.getParameter("trade_status");

            Boolean isSuccess=false;

            //交易为即时到账，交易完成不可退款
            if("TRADE_FINISHED".equals(trade_status)){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知

            //交易还未彻底完成，卖家可以执行退款操作
            }else if ("TRADE_SUCCESS".equals(trade_status)){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //订单的业务逻辑在这里写（最终验证都通过后）


                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
                isSuccess=true;
            }
            //如果成功，需要本路由返回一个success字串
            return isSuccess? "success":"failure";
        }else {
            //验证失败
            return "failure";
        }
    }


    /**
     * 同步回调界面
     * @return 显示界面
     */
    @RequestMapping("/synchronize")
    public String synchronize(HttpServletRequest request) throws Exception {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        EmailUtils.sendEmail("1725367974@qq.com","同步回调页面",params.toString());


        boolean signVerified = Factory.Payment.Common().verifyNotify(params);

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            return "trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount;
        }else {
            return "检验失败";
        }
        //——请在这里编写您的程序（以上代码仅作参考）——
    }






}
