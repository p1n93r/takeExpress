package com.express.service.impl.user;

import com.express.domain.UserExpress;
import com.express.domain.UserExpressExample;
import com.express.mapper.UserExpressMapper;
import com.express.service.user.UserExpressService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @author P1n93r
 */
@Service
public class UserExpressServiceImpl implements UserExpressService {
    @Resource
    UserExpressMapper mapper;


    @Override
    public String findUserExpress(Integer uid) throws Exception {
        UserExpressExample userExpressExample = new UserExpressExample();
        userExpressExample.or().andUidEqualTo(uid);
        userExpressExample.setOrderByClause("num");
        List<UserExpress> result = mapper.selectByExample(userExpressExample);
        if(result!=null){
            JSONObject jsonObject = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            for(UserExpress v:result){
                JSONObject temp = new JSONObject();
                temp.put("no",v.getNum());
                temp.put("expressId",v.getExpressId());
                jsonArray.add(temp);
            }
            jsonObject.put("data",jsonArray);
            return jsonObject.toString();
        }
        return null;
    }

    @Override
    public Boolean deleteUserExpressById(Integer id) throws Exception {
        int i = mapper.deleteByPrimaryKey(id);
        return i>0? true:false;
    }

    @Override
    public Boolean insertUserExpress(UserExpress userExpress) throws Exception {
        int insert=0;
        //先查询是否存在了
        UserExpressExample userExpressExample = new UserExpressExample();
        userExpressExample.or().andNumEqualTo(userExpress.getNum());
        List<UserExpress> has = mapper.selectByExample(userExpressExample);
        if(has.isEmpty()){
            insert = mapper.insert(userExpress);
        }
        return insert>0? true:false;
    }
}
