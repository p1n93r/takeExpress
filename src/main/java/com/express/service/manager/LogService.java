package com.express.service.manager;

import com.express.domain.Log;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Cassie
 */
public interface LogService {


    /**删除一条日志记录
     * @param logId
     * @return
     * @throws Exception
     */
    public Integer deleteOneLog(Integer logId)throws Exception;

    /**批量删除日志记录
     * @param logId
     * @return
     * @throws Exception
     */
    public Integer deleteSomeLog(List<Integer> logId)throws Exception;

    /**删除所有日志记录
     *
     * @return
     * @throws Exception
     */
    public Integer deleteAllLog()throws Exception;

    /**查找所有日志记录
     * @return
     * @throws Exception
     */
    public String selectAllLog()throws Exception;


    /**删除一条日志操作记录
     * @param logId
     * @return
     * @throws Exception
     */
    public Integer deleteOneLogOperation(Integer logId)throws Exception;

    /**批量删除日志操作记录
     * @param logId
     * @return
     * @throws Exception
     */
    public Integer deleteSomeLogOperation(List<Integer> logId)throws Exception;

    /**删除所有日志操作记录
     *
     * @return
     * @throws Exception
     */
    public Integer deleteAllLogOperation()throws Exception;

    /**查找所有日志操作记录
     * @return
     * @throws Exception
     */
    public String selectAllLogOperation()throws Exception;



}
