package com.virginia.service;

import com.virginia.pojo.Activity;
import com.virginia.pojo.PageBean;
import com.virginia.query.GetActivitiesQuery;

public interface ActitvityService {
    Integer addActivity(Activity activity);
    Integer editActivity(Activity activity);
    PageBean getAllActivities(GetActivitiesQuery query);

}
