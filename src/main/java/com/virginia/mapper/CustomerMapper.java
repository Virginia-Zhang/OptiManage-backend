package com.virginia.mapper;

import com.virginia.annotation.DataFilterAnnotation;
import com.virginia.pojo.Clue;
import com.virginia.pojo.Customer;
import com.virginia.query.GetCluesQuery;
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
    @DataFilterAnnotation(tableAlias = "tc", tableField = "owner_id")
    List<Customer> selectAll(GetCustomersQuery query);

    // Remove/Restore customers in batches
    int updateCustomersByClueIds(@Param("clueIds") List<Integer> clueIds, @Param("isDeletedValue") Integer isDeletedValue, @Param("editTime") LocalDateTime editTime, @Param("editBy") Integer editBy);

    // Query count of customer data based on clueId
    int selectCustomerCountByClueId(Integer clueId);
}