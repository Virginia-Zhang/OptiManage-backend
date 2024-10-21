package com.virginia.mapper;

import com.virginia.pojo.TranRemark;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TranRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TranRemark record);

    int insertSelective(TranRemark record);

    TranRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TranRemark record);

    int updateByPrimaryKey(TranRemark record);
}