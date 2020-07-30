package com.express.service.impl.user;

import com.express.domain.*;
import com.express.mapper.DemandMapper;
import com.express.mapper.DemandOrderMapper;
import com.express.mapper.UserMapper;
import com.express.service.user.DemandOrderMngService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author p1n93r
 */
@Service
public class DemandOrderMngImpl implements DemandOrderMngService {

    /**
     * 需求表接口
     */
    @Resource
    DemandMapper demandMapper;

    /**
     * 需求订单表接口
     */
    @Resource
    DemandOrderMapper demandOrderMapper;


    /**
     * 用户表接口
     */
    @Resource
    UserMapper userMapper;


    @Override
    public String findDemandOrder(Integer uid) throws Exception {
        //查询当前用户下的已发布的需求
        DemandExample demandExample = new DemandExample();
        demandExample.or().andUidEqualTo(uid);
        List<Demand> demands = demandMapper.selectByExample(demandExample);
        JSONObject result = new JSONObject();
        //如果查询到了需求
        if(demands!=null){
            result.put("total",demands.size());
            //遍历需求，查找是否被接单
            for (Demand v:demands) {
                JSONArray rows = new JSONArray();
                JSONObject row = new JSONObject();
                row.put("demandId",v.getDemandId());
                row.put("price",v.getPrice());
                row.put("status",v.getStatus());
                //描述信息只接收10个字符
                row.put("desc",v.getDescription().substring(0,15)+"...");
                DemandOrderExample demandOrderExample = new DemandOrderExample();
                demandOrderExample.or().andDemandIdEqualTo(v.getDemandId());
                List<DemandOrder> demandOrders = demandOrderMapper.selectByExample(demandOrderExample);
                if(demandOrders!=null){
                    //如果被接单，则继续完善row
                    for (DemandOrder iv:demandOrders) {
                        row.put("orderNumber",iv.getOrderNumber());
                        row.put("createTime",iv.getCreateTime());
                        row.put("returnTime",iv.getReturnTime());
                        //获取代取人真实姓名
                        Integer whoId=iv.getUid();
                        if(whoId!=null){
                            User user = userMapper.selectByPrimaryKey(whoId);
                            row.put("who",user.getName());
                        }
                    }
                }
                rows.add(row);
                result.put("rows",rows);
            }
        }
        return result.toString();
    }
}
