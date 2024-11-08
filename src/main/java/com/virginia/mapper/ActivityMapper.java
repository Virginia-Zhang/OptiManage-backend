package com.virginia.mapper;

import com.virginia.annotation.DataFilterAnnotation;
import com.virginia.pojo.Activity;
import com.virginia.query.DataFilterQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    // Query all marketing campaigns/activities
    // The parameters passed in are dynamic query conditions, that is, dynamically spliced ​​SQL statements, used for data permission control.
    @DataFilterAnnotation(tableAlias = "ta", tableField = "owner_id")
    List<Activity> selectAll(DataFilterQuery query);
}