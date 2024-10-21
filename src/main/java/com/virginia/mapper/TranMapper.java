package com.virginia.mapper;

import com.virginia.pojo.Tran;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TranMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Tran record);

    int insertSelective(Tran record);

    Tran selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Tran record);

    int updateByPrimaryKey(Tran record);
}