package com.virginia.service;

import com.virginia.pojo.ActivityRemark;

import java.util.List;

/**
 * Service interface for marketing activity remark
 * @author Virginia
 */
public interface ActivityRemarkService {
    Integer addActivityRemark(ActivityRemark activityRemark);
    Integer deleteActivityRemarkById(Integer id);
    Integer editActivityRemarkById(ActivityRemark activityRemark);
    List<ActivityRemark> getAllActivityRemarks(Integer activityId);
}
