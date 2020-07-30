package com.express.service.user;

import com.express.domain.UserExpress;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

/**
 * @author P1n93r
 */
public interface UserExpressService {
    /**
     * 查找当前用户的历史快递单号搜索记录
     * @param uid 用户id
     * @return 用户的快递查询单号的历史记录
     * @throws Exception 异常
     */
    public String findUserExpress(Integer uid) throws Exception;

    /**
     * 删除用户的历史快递单号搜索记录
     * @param id 被删除的记录的主键
     * @return 是否成功
     * @throws Exception 异常
     */
    public Boolean deleteUserExpressById(Integer id) throws Exception;

    /**
     * 添加一个用户快递查询搜索记录
     * @param userExpress 用户的快打单号实体
     * @param userExpress 验证结果
     * @return 是否成功
     * @throws Exception 异常
     */
    public Boolean insertUserExpress(UserExpress userExpress) throws Exception;

}
