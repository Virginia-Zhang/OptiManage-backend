package com.virginia.mapper;

import com.virginia.pojo.Activity;
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

    // Query all marketing campaigns/activities
    List<Activity> selectAll();
}