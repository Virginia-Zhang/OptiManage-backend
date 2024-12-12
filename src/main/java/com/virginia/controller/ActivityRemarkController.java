package com.virginia.controller;

import com.virginia.pojo.ActivityRemark;
import com.virginia.query.GetActivityRemarksQuery;
import com.virginia.result.R;
import com.virginia.service.ActivityRemarkService;
import com.virginia.validation.ValidationGroups;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/activityRemark")
public class ActivityRemarkController {
    @Resource
    private ActivityRemarkService activityRemarkService;

    @PreAuthorize("hasAuthority('activityRemark:add')")
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

    @PreAuthorize("hasAuthority('activityRemark:list')")
    @GetMapping("/list")
    public R getAllActivityRemarks(@Validated GetActivityRemarksQuery query) {
        return R.SUCCESS(activityRemarkService.getAllActivityRemarks(query.getActivityId(), query.getPage(), query.getPageSize(), query.getIsDeleted()));
    }

    @PreAuthorize("hasAuthority('activityRemark:edit')")
    @PutMapping("/")
    public R editActivityRemarkById(@Validated(ValidationGroups.EditActivityRemarkGroup.class) @RequestBody ActivityRemark activityRemark) {
        try {
            Integer result = activityRemarkService.editActivityRemarkById(activityRemark);
            return result > 0 ? R.SUCCESS(result) : R.FAIL("Edit marketing activity remark failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Edit marketing activity remark failed!Please try again!");
        }
    }

    @PreAuthorize("hasAuthority('activityRemark:delete')")
    @DeleteMapping("/{id}")
    public R deleteActivityRemarkById(@PathVariable Integer id) {
        try {
            Integer result = activityRemarkService.deleteActivityRemarkById(id);
            return result > 0 ? R.SUCCESS(result) : R.FAIL("Delete marketing activity remark failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Delete marketing activity remark failed!Please try again!");
        }
    }
}