package com.virginia.mapper;

import com.virginia.annotation.DataFilterAnnotation;
import com.virginia.pojo.Activity;
import com.virginia.query.GetActivitiesQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ActivityMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Activity record);

    int insertSelective(Activity record);

    Activity selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Activity record);

    int updateByPrimaryKey(Activity record);

    // Query marketing campaigns/activities with searching parameters, data filter SQL and pagination.
    // The parameters passed in are searching parameters with filterSQL used for data permission control.
    @DataFilterAnnotation(tableAlias = "ta", tableField = "owner_id")
    List<Activity> selectAll(GetActivitiesQuery query);
}