package com.virginia.mapper;

import com.virginia.pojo.CustomerRemark;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerRemark record);

    int insertSelective(CustomerRemark record);

    CustomerRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerRemark record);

    int updateByPrimaryKey(CustomerRemark record);
}