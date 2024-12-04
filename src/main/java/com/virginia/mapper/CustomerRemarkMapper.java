package com.virginia.mapper;

import com.virginia.pojo.ClueRemark;
import com.virginia.pojo.CustomerRemark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CustomerRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CustomerRemark record);

    int insertSelective(CustomerRemark record);

    CustomerRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CustomerRemark record);

    int updateByPrimaryKey(CustomerRemark record);

    // According to the customer ID, query all undeleted/deleted customer remarks under the current customer.
    List<CustomerRemark> selectAll(@Param("customerId") Integer customerId, @Param("isDeletedValue") Integer isDeletedValue);
}