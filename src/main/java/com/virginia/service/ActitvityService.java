package com.virginia.service;

import com.virginia.pojo.Activity;
import com.virginia.pojo.PageBean;

public interface ActitvityService {
    Integer addActivity(Activity activity);
    Integer editActivity(Activity activity);
    PageBean getAllActivities(Integer page, Integer pageSize);

}
