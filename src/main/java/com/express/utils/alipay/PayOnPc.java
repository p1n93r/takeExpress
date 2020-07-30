package com.express.utils.alipay;

import com.alipay.easysdk.factory.Factory;
import com.alipay.easysdk.payment.common.models.AlipayTradeCancelResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeQueryResponse;
import com.alipay.easysdk.payment.common.models.AlipayTradeRefundResponse;
import com.alipay.easysdk.payment.facetoface.models.AlipayTradePrecreateResponse;
import com.alipay.easysdk.payment.page.models.AlipayTradePagePayResponse;
import net.sf.json.JSON;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.UUID;

/**
 * 基于网站的支付
 * @author Cassie
 */
public class PayOnPc {

    static {
        // 1. 设置参数（全局只需设置一次）
        Factory.setOptions(GetConfig.get());
    }

    /**
     * @param subject 交易订单的主题，例如：苹果6plus
     * @param num 交易订单的订单号，在全站中此订单号需要唯一
     * @param money 交易金额
     * @return 一个二维码链接
     */
    public static JSONObject pay(String subject,String num,String money){
        JSONObject res = new JSONObject();
        res.put("result",false);
        try {
            AlipayTradePrecreateResponse response = Factory.Payment.FaceToFace().preCreate(subject, num, money);
            res.put("result",true);
            res.put("num",num);
            res.put("url",response.qrCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    /**
     * 提供查询支付状态的接口
     * @param num 商家订单号
     * @return 支付宝返回的结果
     * @throws Exception 异常
     */
   public static JSONObject query(String num){
       String body;
       try {
           body = Factory.Payment.Common().query(num).httpBody;
       } catch (Exception e) {
           JSONObject error = new JSONObject();
           error.put("code","400");
           error.put("msg","订单不存在");
           return error;
       }
       return (JSONObject) JSONObject.fromObject(body).get("alipay_trade_query_response");
   }


    /**
     * 取消未完成的订单，当主动查询发现为支付时，需要进行交易撤销
     * @param num 订单号
     * @return 是否撤销成功
     */
   public static Boolean cancelPay(String num){
       try {
           //alipay_trade_cancel_response
           String body = Factory.Payment.Common().cancel(num).httpBody;
           JSONObject res = (JSONObject) JSONObject.fromObject(body).get("alipay_trade_cancel_response");
           if(res.has("code")&&res.has("msg")){
               String code = res.get("code").toString();
               String msg = res.get("msg").toString();
               return "Success".equals(msg) && "10000".equals(code);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }


    /**
     * 对订单进行退款，只有当订单金额与退款金额一致时才能退款成功
     * @param num 订单号
     * @param money 退款金额
     * @return 是否退款成功
     */
   public static Boolean refund(String num,String money){
       try {
           String body = Factory.Payment.Common().refund(num, money).httpBody;
           JSONObject res = (JSONObject) JSONObject.fromObject(body).get("alipay_trade_refund_response");
           System.out.println(res);
           if(res.has("code")&&res.has("msg")){
               String code = res.get("code").toString();
               String msg = res.get("msg").toString();
               return "Success".equals(msg) && "10000".equals(code);
           }
       } catch (Exception e) {
           e.printStackTrace();
       }
       return false;
   }



}
