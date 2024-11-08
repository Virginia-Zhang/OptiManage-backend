package com.virginia.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.virginia.mapper.ActivityMapper;
import com.virginia.pojo.Activity;
import com.virginia.pojo.PageBean;
import com.virginia.service.ActitvityService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActivityServiceImpl implements ActitvityService {
    @Resource
    private ActivityMapper activityMapper;

    @Override
    public Integer addActivity(Activity activity) {
        return 0;
    }

    @Override
    public Integer editActivity(Activity activity) {
        return 0;
    }

    /**
     *Query all activities by page
     * @param page page number
     * @param pageSize Number of records per page
     * @return PageBean, containing the total number of records and the current page records
     */
    @Override
    public PageBean getAllActivities(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Activity> activityList = activityMapper.selectAll();
        Page<Activity> pageInfo = (Page<Activity>) activityList;
        return new PageBean(pageInfo.getTotal(), pageInfo.getResult());
    }
}
