package com.virginia.mapper;

import com.virginia.annotation.DataFilterByRegionAnnotation;
import com.virginia.annotation.DataFilterByUserAnnotation;
import com.virginia.pojo.Customer;
import com.virginia.query.GetCustomersQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface CustomerMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKey(Customer record);

    // Query customers/clients with searching parameters, data filter SQL and pagination.
    // The parameters passed in are searching parameters with filterSQL used for data permission control.
    @DataFilterByUserAnnotation(tableAlias = "tc", tableField = "owner_id")
    @DataFilterByRegionAnnotation(tableAlias = "tc", tableField = "region")
    List<Customer> selectAll(GetCustomersQuery query);

    // Remove/Restore customers in batches
    int updateCustomersByClueIds(@Param("clueIds") List<Integer> clueIds, @Param("isDeletedValue") Integer isDeletedValue, @Param("editTime") LocalDateTime editTime, @Param("editBy") Integer editBy);

    // Query count of customer data based on clueId
    int selectCustomerCountByClueId(Integer clueId);
}