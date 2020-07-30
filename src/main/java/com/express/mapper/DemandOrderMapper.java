package com.express.mapper;

import com.express.domain.DemandOrder;
import com.express.domain.DemandOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DemandOrderMapper {
    long countByExample(DemandOrderExample example);

    int deleteByExample(DemandOrderExample example);

    int deleteByPrimaryKey(Integer orderId);

    int insert(DemandOrder record);

    int insertSelective(DemandOrder record);

    List<DemandOrder> selectByExample(DemandOrderExample example);

    DemandOrder selectByPrimaryKey(Integer orderId);

    int updateByExampleSelective(@Param("record") DemandOrder record, @Param("example") DemandOrderExample example);

    int updateByExample(@Param("record") DemandOrder record, @Param("example") DemandOrderExample example);

    int updateByPrimaryKeySelective(DemandOrder record);

    int updateByPrimaryKey(DemandOrder record);
}