package com.virginia.service.impl;

import com.virginia.annotation.LogAnnotation;
import com.virginia.mapper.ActivityRemarkMapper;
import com.virginia.pojo.ActivityRemark;
import com.virginia.pojo.MyUserDetails;
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
        // Get the ID of the currently logged in user from the security context holder
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        activityRemark.setCreateBy(loggedInUserInfo.getUser().getId());
        return activityRemarkMapper.insertSelective(activityRemark);
    }

    @Override
    public Integer deleteActivityRemarkById(Integer id) {
        return 0;
    }

    @Override
    public Integer editActivityRemarkById(ActivityRemark activityRemark) {
        return 0;
    }

    @Override
    public List<ActivityRemark> getAllActivityRemarks(Integer activityId) {
        return List.of();
    }
}
