package com.virginia.controller;

import com.virginia.pojo.Activity;
import com.virginia.pojo.PageBean;
import com.virginia.query.GetActivitiesQuery;
import com.virginia.result.R;
import com.virginia.service.impl.ActivityServiceImpl;
import com.virginia.utils.UserUtils;
import com.virginia.validation.ValidationGroups;
import jakarta.annotation.Resource;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/activity")
public class ActivityController {
    @Resource
    private ActivityServiceImpl activityService;

    /**
     * Query activity data by page (with searching params) and return
     * @param query, encapsulated with searching params and paging params
     * @return paging data, the format is: {total: 100, rows: [{}, {}, ...]}, encapsulated into R: data
     */
    @GetMapping("/list")
    public R getAllActivities(GetActivitiesQuery query) {
        PageBean activities = activityService.getAllActivities(query);
        return R.SUCCESS(activities);
    }

    /**
     * Add new marketing activity
     * @param activity object
     * @return number of rows affected, encapsulated into R: data
     */
    @PostMapping("/")
    public R addActivity(@Validated @RequestBody Activity activity, BindingResult bindingResult) throws MethodArgumentNotValidException {
        // Perform non-null verification on costRmb/costUsd/costJpy field
        if(activity.getRegion() == 1 && activity.getCostRmb() == null){
            bindingResult.addError(new FieldError("activity","costRmb", "Cost in RMB is required!"));
        }else if (activity.getRegion() == 2 && activity.getCostJpy() == null){
            bindingResult.addError(new FieldError("activity","costJpy", "Cost in JPY is required!"));
        } else if (activity.getRegion() != 1 && activity.getRegion() != 2 && activity.getCostUsd() == null) {
            bindingResult.addError(new FieldError("activity","costUsd", "Cost in USD is required!"));
        }

        // Perform non-empty verification on the ownerId field. When the logged-in user's roleList includes admin and the ownerId is empty, the verification fails and an error message is prompted.
        List<String> roleList = Objects.requireNonNull(UserUtils.getLoggedInUserInfo()).getRoleList();
        if (roleList.contains("admin") && activity.getOwnerId() == null) {
            bindingResult.addError(new FieldError("activity","ownerId", "Owner ID is required!"));
        }

        // If any of the above fields fails in verification, MethodArgumentNotValidException will be thrown manually.
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        try {
            Integer result = activityService.addActivity(activity);
            return result >= 1 ? R.SUCCESS(result) : R.FAIL("Add marketing activity failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Add marketing activity failed!Please try again!");
        }
    }

    /**
     * Edit marketing activity
     * @param activity object
     * @return number of rows affected, encapsulated into R: data
     */
    @PutMapping("/")
    public R editActivity(@Validated(ValidationGroups.EditActivityGroup.class) @RequestBody Activity activity, BindingResult bindingResult) throws MethodArgumentNotValidException {
        // Perform non-null verification on costRmb/costUsd/costJpy field
        if(activity.getRegion() == 1 && activity.getCostRmb() == null){
            bindingResult.addError(new FieldError("activity","costRmb", "Cost in RMB is required!"));
        }else if (activity.getRegion() == 2 && activity.getCostJpy() == null){
            bindingResult.addError(new FieldError("activity","costJpy", "Cost in JPY is required!"));
        }else if (activity.getRegion() != 1 && activity.getRegion() != 2 && activity.getCostUsd() == null){
            bindingResult.addError(new FieldError("activity","costUsd", "Cost in USD is required!"));
        }

        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        try {
            Integer result = activityService.editActivity(activity);
            return result >= 1 ? R.SUCCESS(result) : R.FAIL("Edit marketing activity failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Edit marketing activity failed!Please try again!");
        }
    }
}
