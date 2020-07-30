package com.express.service.impl.manager;

import com.express.domain.Log;
import com.express.domain.LogExample;
import com.express.domain.LogOperation;
import com.express.domain.LogOperationExample;
import com.express.mapper.LogMapper;
import com.express.mapper.LogOperationMapper;
import com.express.service.manager.LogService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
/**
 * @author Cassie
 */
@Service
public class LogServiceImpl implements LogService {
    @Resource
    LogMapper logMapper;

    @Resource
    LogOperationMapper logOperationMapper;







    @Override
    public Integer deleteOneLog(Integer logId) throws Exception {
        int i = logMapper.deleteByPrimaryKey(logId);
        return i;
    }

    @Override
    public Integer deleteSomeLog(List<Integer> logId) throws Exception {
        LogExample logExample = new LogExample();
        logExample.or().andLogIdIn(logId);
        int i = logMapper.deleteByExample(logExample);
        return i;
    }

    @Override
    public Integer deleteAllLog() throws Exception {
        LogExample logExample = new LogExample();
        logExample.or();
        int i = logMapper.deleteByExample(logExample);
        return i;
    }

    @Override
    public String selectAllLog() throws Exception {
        LogExample logExample = new LogExample();
        logExample.or();
        List<Log> logs = logMapper.selectByExample(logExample);
        JSONObject jsonObject = new JSONObject();
        if(logs!=null&&logs.size()>0){
            JSONArray jsonArray = new JSONArray();
            for(Log v:logs){
                JSONObject json = new JSONObject();
                json.put("logId",v.getLogId());
                json.put("logTime",v.getLogTime());
                json.put("location",v.getLocation());
                json.put("isSuccess",v.getIsSuccess());
                json.put("ip",v.getIp());
                jsonArray.add(json);
            }
            jsonObject.put("data",jsonArray);
        }
        return jsonObject.toString();
    }

    /**
     * 删除一条日志操作记录
     *
     * @param logId
     * @return
     * @throws Exception
     */
    @Override
    public Integer deleteOneLogOperation(Integer logId) throws Exception {
        int i = logOperationMapper.deleteByPrimaryKey(logId);
        return i;
    }

    /**
     * 批量删除日志操作记录
     *
     * @param logId
     * @return
     * @throws Exception
     */
    @Override
    public Integer deleteSomeLogOperation(List<Integer> logId) throws Exception {
        LogOperationExample logOperationExample = new LogOperationExample();
        logOperationExample.or().andLogOpidIn(logId);
        int i = logOperationMapper.deleteByExample(logOperationExample);
        return i;
    }

    /**
     * 删除所有日志操作记录
     *
     * @return
     * @throws Exception
     */
    @Override
    public Integer deleteAllLogOperation() throws Exception {
        LogOperationExample logOperationExample = new LogOperationExample();
        logOperationExample.or();
        int i = logOperationMapper.deleteByExample(logOperationExample);
        return i;
    }

    /**
     * 查找所有日志操作记录
     *
     * @return
     * @throws Exception
     */
    @Override
    public String selectAllLogOperation() throws Exception {
        LogOperationExample logOperationExample = new LogOperationExample();
        logOperationExample.or();
        List<LogOperation> logOperations = logOperationMapper.selectByExample(logOperationExample);
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for(LogOperation v:logOperations){
            JSONObject json = new JSONObject();
            json.put("logOpId",v.getLogOpid());
            json.put("logOpType",v.getLogOptype());
            json.put("logOpTime",v.getLogOptime());
            json.put("logOpContent",v.getLogOpcontent());
            jsonArray.add(json);
        }
        jsonObject.put("data",jsonArray);
        return jsonObject.toString();
    }
}
