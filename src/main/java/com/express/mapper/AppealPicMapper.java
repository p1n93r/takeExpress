package com.express.mapper;

import com.express.domain.AppealPic;
import com.express.domain.AppealPicExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppealPicMapper {
    long countByExample(AppealPicExample example);

    int deleteByExample(AppealPicExample example);

    int insert(AppealPic record);

    int insertSelective(AppealPic record);

    List<AppealPic> selectByExample(AppealPicExample example);

    int updateByExampleSelective(@Param("record") AppealPic record, @Param("example") AppealPicExample example);

    int updateByExample(@Param("record") AppealPic record, @Param("example") AppealPicExample example);
}