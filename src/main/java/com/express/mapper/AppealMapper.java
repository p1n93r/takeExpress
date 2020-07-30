package com.express.mapper;

import com.express.domain.Appeal;
import com.express.domain.AppealExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppealMapper {
    long countByExample(AppealExample example);

    int deleteByExample(AppealExample example);

    int deleteByPrimaryKey(Integer appealId);

    int insert(Appeal record);

    int insertSelective(Appeal record);

    List<Appeal> selectByExample(AppealExample example);

    Appeal selectByPrimaryKey(Integer appealId);

    int updateByExampleSelective(@Param("record") Appeal record, @Param("example") AppealExample example);

    int updateByExample(@Param("record") Appeal record, @Param("example") AppealExample example);

    int updateByPrimaryKeySelective(Appeal record);

    int updateByPrimaryKey(Appeal record);
}