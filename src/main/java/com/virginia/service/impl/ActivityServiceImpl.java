package com.virginia.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.virginia.annotation.LogAnnotation;
import com.virginia.mapper.ActivityMapper;
import com.virginia.pojo.Activity;
import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.query.GetActivitiesQuery;
import com.virginia.service.ActivityService;
import com.virginia.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Resource
    private ActivityMapper activityMapper;

    @LogAnnotation
    @Override
    public Integer addActivity(Activity activity) {
        // If the owner id is empty, set its value to the id of the currently logged in user.
        if(activity.getOwnerId() == null){
            MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
            assert loggedInUserInfo != null;
            activity.setOwnerId(loggedInUserInfo.getUser().getId());
        }
        // Add create time and create by
        activity.setCreateTime(LocalDateTime.now());
        // Get the ID of the currently logged in user from the security context holder
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        activity.setCreateBy(loggedInUserInfo.getUser().getId());
        return activityMapper.insertSelective(activity);
    }

    @LogAnnotation
    @Override
    public Integer editActivity(Activity activity) {
        // Obtain the ID of the currently logged in user from the security context and assign it to editBy field.
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        activity.setEditBy(loggedInUserInfo.getUser().getId());
        // Set editTime
        activity.setEditTime(LocalDateTime.now());
        return activityMapper.updateByPrimaryKeySelective(activity);
    }

    /**
     *Query all activities by page and other conditions
     * @param query, which contains the current page number, page size, and other query conditions
     * @return PageBean, containing the total number of records and the current page records
     */
    @Override
    public PageBean getAllActivities(GetActivitiesQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        // Pass in the object of GetActivitiesQuery, a subclass of DataFilterQuery. This object contains filterSQL, paging and conditional query parameters.
        // filterSQL is used for data permission control. Except for admin, only the market activities that the current user is responsible for are queried.
        List<Activity> activityList = activityMapper.selectAll(query);
        Page<Activity> pageInfo = (Page<Activity>) activityList;
        return new PageBean(pageInfo.getTotal(), pageInfo.getResult());
    }


    @LogAnnotation
    @Override
    /**
     * Delete/Restore activities by IDs
     * @param ids: IDs of the activities to be deleted or restored
     * @param isDeletedValue: 1 for delete, 0 for restore
     * @return: Number of rows affected
     */
    public Integer updateActivitiesByIds(List<Integer> ids, Integer isDeletedValue) {
        // Update edit_time and edit_by at the same time
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        return activityMapper.updateActivitiesByIds(ids, isDeletedValue, LocalDateTime.now(), loggedInUserInfo.getUser().getId());
    }
}
