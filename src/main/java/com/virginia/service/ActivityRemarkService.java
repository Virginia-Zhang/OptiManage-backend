package com.virginia.service;

import com.virginia.pojo.ActivityRemark;
import com.virginia.pojo.PageBean;

/**
 * Service interface for marketing activity remark feature
 * @author Virginia
 */
public interface ActivityRemarkService {
    Integer addActivityRemark(ActivityRemark activityRemark);
    Integer deleteActivityRemarkById(Integer id);
    Integer editActivityRemarkById(ActivityRemark activityRemark);
    PageBean getAllActivityRemarks(Integer activityId, Integer page, Integer pageSize, Integer isDeletedValue);
}
