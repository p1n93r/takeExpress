package com.express.service.user;

/**
 * 需求方订单管理：需要注意，防止越权漏洞
 * @author p1n93r
 */
public interface DemandOrderMngService {

    /**
     * 查询当前用户下的需求订单
     * @param uid 用户的uid
     * @return 查询结果：json格式
     * @throws Exception 异常
     */
    public String findDemandOrder(Integer uid) throws Exception;



}
