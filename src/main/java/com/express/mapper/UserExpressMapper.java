package com.express.mapper;

import com.express.domain.UserExpress;
import com.express.domain.UserExpressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserExpressMapper {
    long countByExample(UserExpressExample example);

    int deleteByExample(UserExpressExample example);

    int deleteByPrimaryKey(Integer expressId);

    int insert(UserExpress record);

    int insertSelective(UserExpress record);

    List<UserExpress> selectByExample(UserExpressExample example);

    UserExpress selectByPrimaryKey(Integer expressId);

    int updateByExampleSelective(@Param("record") UserExpress record, @Param("example") UserExpressExample example);

    int updateByExample(@Param("record") UserExpress record, @Param("example") UserExpressExample example);

    int updateByPrimaryKeySelective(UserExpress record);

    int updateByPrimaryKey(UserExpress record);
}