package com.virginia.mapper;

import com.virginia.pojo.ActivityRemark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ActivityRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityRemark record);

    int insertSelective(ActivityRemark record);

    ActivityRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityRemark record);

    int updateByPrimaryKey(ActivityRemark record);

    // According to the activity ID, query all undeleted/deleted notes under the current activity.
    List<ActivityRemark> selectAll(@Param("activityId") Integer activityId, @Param("isDeletedValue") Integer isDeletedValue);
}