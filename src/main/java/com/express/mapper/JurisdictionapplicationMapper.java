package com.express.mapper;

import com.express.domain.Jurisdictionapplication;
import com.express.domain.JurisdictionapplicationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface JurisdictionapplicationMapper {
    long countByExample(JurisdictionapplicationExample example);

    int deleteByExample(JurisdictionapplicationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Jurisdictionapplication record);

    int insertSelective(Jurisdictionapplication record);

    List<Jurisdictionapplication> selectByExample(JurisdictionapplicationExample example);

    Jurisdictionapplication selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Jurisdictionapplication record, @Param("example") JurisdictionapplicationExample example);

    int updateByExample(@Param("record") Jurisdictionapplication record, @Param("example") JurisdictionapplicationExample example);

    int updateByPrimaryKeySelective(Jurisdictionapplication record);

    int updateByPrimaryKey(Jurisdictionapplication record);
}