package com.express.mapper;

import com.express.domain.Register;
import com.express.domain.RegisterExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RegisterMapper {
    long countByExample(RegisterExample example);

    int deleteByExample(RegisterExample example);

    int deleteByPrimaryKey(Integer registerId);

    int insert(Register record);

    int insertSelective(Register record);

    List<Register> selectByExample(RegisterExample example);

    Register selectByPrimaryKey(Integer registerId);

    int updateByExampleSelective(@Param("record") Register record, @Param("example") RegisterExample example);

    int updateByExample(@Param("record") Register record, @Param("example") RegisterExample example);

    int updateByPrimaryKeySelective(Register record);

    int updateByPrimaryKey(Register record);
}