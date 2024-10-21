package com.virginia.mapper;

import com.virginia.pojo.TranHistory;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TranHistoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TranHistory record);

    int insertSelective(TranHistory record);

    TranHistory selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TranHistory record);

    int updateByPrimaryKey(TranHistory record);
}