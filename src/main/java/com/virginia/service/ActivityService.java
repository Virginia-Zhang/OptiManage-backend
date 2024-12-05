package com.virginia.service;

import com.virginia.pojo.Activity;
import com.virginia.pojo.PageBean;
import com.virginia.query.GetActivitiesQuery;

import java.util.List;

public interface ActivityService {
    Integer addActivity(Activity activity);
    Integer editActivity(Activity activity);
    PageBean getAllActivities(GetActivitiesQuery query);
    // Remove/Restore activities by ids
    Integer updateActivitiesByIds(List<Integer> ids, Integer isDeletedValue);
    // Get all activities with id and name only
    List<Activity> getActivities();
    // Get all activities without pagination
    List<Activity> getAllActivitiesWithoutPagination(GetActivitiesQuery query);
}
