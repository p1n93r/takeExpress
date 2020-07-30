package com.express.mapper;

import com.express.domain.LogOperation;
import com.express.domain.LogOperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogOperationMapper {
    long countByExample(LogOperationExample example);

    int deleteByExample(LogOperationExample example);

    int deleteByPrimaryKey(Integer logOpid);

    int insert(LogOperation record);

    int insertSelective(LogOperation record);

    List<LogOperation> selectByExample(LogOperationExample example);

    LogOperation selectByPrimaryKey(Integer logOpid);

    int updateByExampleSelective(@Param("record") LogOperation record, @Param("example") LogOperationExample example);

    int updateByExample(@Param("record") LogOperation record, @Param("example") LogOperationExample example);

    int updateByPrimaryKeySelective(LogOperation record);

    int updateByPrimaryKey(LogOperation record);
}