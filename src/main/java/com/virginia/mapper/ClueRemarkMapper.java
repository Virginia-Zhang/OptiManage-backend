package com.virginia.mapper;

import com.virginia.pojo.ClueRemark;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClueRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClueRemark record);

    int insertSelective(ClueRemark record);

    ClueRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClueRemark record);

    int updateByPrimaryKey(ClueRemark record);
}