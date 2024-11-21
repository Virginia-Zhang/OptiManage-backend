package com.virginia.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.virginia.annotation.LogAnnotation;
import com.virginia.mapper.ActivityRemarkMapper;
import com.virginia.pojo.ActivityRemark;
import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.service.ActivityRemarkService;
import com.virginia.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ActivityRemarkServiceImpl implements ActivityRemarkService {
    @Resource
    private ActivityRemarkMapper activityRemarkMapper;

    @LogAnnotation
    @Override
    public Integer addActivityRemark(ActivityRemark activityRemark) {
        // Add create time and create by
        activityRemark.setCreateTime(LocalDateTime.now());
        // Get the data of the currently logged-in user from security context holder
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        activityRemark.setCreateBy(loggedInUserInfo.getUser().getId());
        return activityRemarkMapper.insertSelective(activityRemark);
    }

    @LogAnnotation
    @Override
    public Integer deleteActivityRemarkById(Integer id) {
        ActivityRemark activityRemark = new ActivityRemark();
        activityRemark.setId(id);
        activityRemark.setIsDeleted(1);
        activityRemark.setEditTime(LocalDateTime.now());
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        activityRemark.setEditBy(loggedInUserInfo.getUser().getId());
        return activityRemarkMapper.updateByPrimaryKeySelective(activityRemark);
    }

    @LogAnnotation
    @Override
    public Integer editActivityRemarkById(ActivityRemark activityRemark) {
        // Add edit time and edit by
        activityRemark.setEditTime(LocalDateTime.now());
        // Get the ID of the currently logged in user from the security context holder
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        activityRemark.setEditBy(loggedInUserInfo.getUser().getId());
        return activityRemarkMapper.updateByPrimaryKeySelective(activityRemark);
    }

    /**
     * Get undeleted activity remarks of an activity by page and page size
     * @param activityId id of the activity
     * @param page page number
     * @param pageSize page size
     * @return list of activity remarks
     */
    @Override
    public PageBean getAllActivityRemarks(Integer activityId, Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<ActivityRemark> activityRemarks = activityRemarkMapper.selectAll(activityId);
        Page<ActivityRemark> pageInfo = (Page<ActivityRemark>) activityRemarks;
        return new PageBean(pageInfo.getTotal(), pageInfo.getResult());
    }
}
