package com.virginia.mapper;

import com.virginia.annotation.DataFilterByRegionAnnotation;
import com.virginia.annotation.DataFilterByUserAnnotation;
import com.virginia.pojo.Activity;
import com.virginia.query.DataFilterQuery;
import com.virginia.query.GetActivitiesQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
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
    @DataFilterByUserAnnotation(tableAlias = "ta", tableField = "owner_id")
    @DataFilterByRegionAnnotation(tableAlias = "ta", tableField = "region")
    List<Activity> selectAll(GetActivitiesQuery query);

    // Remove/Restore activities in batches
    int updateActivitiesByIds(@Param("ids") List<Integer> ids, @Param("isDeletedValue") Integer isDeletedValue, @Param("editTime") LocalDateTime editTime, @Param("editBy") Integer editBy);

    // Get all activities with id, name and region only
    @DataFilterByRegionAnnotation(tableAlias = "ta", tableField = "region")
    List<Activity> selectAllActivities(DataFilterQuery query);
}