package com.virginia.mapper;

import com.virginia.pojo.DicType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DicTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DicType record);

    int insertSelective(DicType record);

    DicType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DicType record);

    int updateByPrimaryKey(DicType record);
}