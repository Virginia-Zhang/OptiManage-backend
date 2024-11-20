package com.virginia.controller;

import com.virginia.pojo.ActivityRemark;
import com.virginia.result.R;
import com.virginia.service.ActivityRemarkService;
import com.virginia.validation.ValidationGroups;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activityRemark")
public class ActivityRemarkController {
    @Resource
    private ActivityRemarkService activityRemarkService;

    @PostMapping("/")
    public R addActivityRemark(@Validated(ValidationGroups.AddActivityRemarkGroup.class) @RequestBody ActivityRemark activityRemark) {
        try {
            Integer result = activityRemarkService.addActivityRemark(activityRemark);
            return result > 0 ? R.SUCCESS(result) : R.FAIL("Add marketing activity remark failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Add marketing activity remark failed!Please try again!");
        }
    }
}
