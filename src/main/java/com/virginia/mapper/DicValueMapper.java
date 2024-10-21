package com.virginia.mapper;

import com.virginia.pojo.DicValue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DicValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicValue record);

    int insertSelective(DicValue record);

    DicValue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicValue record);

    int updateByPrimaryKey(DicValue record);
}