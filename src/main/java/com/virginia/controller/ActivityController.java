package com.virginia.controller;

import com.virginia.pojo.Activity;
import com.virginia.pojo.PageBean;
import com.virginia.query.BatchUpdateQuery;
import com.virginia.query.GetActivitiesQuery;
import com.virginia.result.R;
import com.virginia.service.impl.ActivityServiceImpl;
import com.virginia.utils.UserUtils;
import com.virginia.validation.ValidationGroups;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('activity:list')")
    public R getAllActivities(@Validated(ValidationGroups.SelectActivitiesGroup.class) GetActivitiesQuery query, BindingResult bindingResult) throws MethodArgumentNotValidException {
        // The activity budget range (startCost) and activity budget currency unit (currencyUnit) must exist at the same time. If one does not exist and the other does exist, MethodArgumentNotValidException will be thrown.
        if ((query.getStartCost() != null && StringUtils.isBlank(query.getCurrencyUnit())) || (query.getStartCost() == null && StringUtils.isNotBlank(query.getCurrencyUnit()))) {
            bindingResult.addError(new FieldError("query","startCost", "Activity budget range(startCost) and activity budget currency unit(currencyUnit) must be both specified!"));
        }
        if (bindingResult.hasErrors()) {
            throw new MethodArgumentNotValidException(null, bindingResult);
        }

        PageBean activities = activityService.getAllActivities(query);
        return R.SUCCESS(activities);
    }

    /**
     * Add new marketing activity
     * @param activity object
     * @return number of rows affected, encapsulated into R: data
     */
    @PreAuthorize("hasAuthority('activity:add')")
    @PostMapping("/")
    public R addActivity(@Validated(ValidationGroups.AddActivityGroup.class) @RequestBody Activity activity, BindingResult bindingResult) throws MethodArgumentNotValidException {
        // Perform non-empty verification on the ownerId field. When the logged-in user's roleList includes admin and the ownerId is empty, the verification fails and an error message is prompted.
        List<String> roleList = Objects.requireNonNull(UserUtils.getLoggedInUserInfo()).getRoleList();
        if (roleList.contains("admin") && activity.getOwnerId() == null) {
            bindingResult.addError(new FieldError("activity","ownerId", "Owner ID is required!"));
        }

        // If the above field fails in verification, MethodArgumentNotValidException will be thrown manually.
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
    @PreAuthorize("hasAuthority('activity:edit')")
    @PutMapping("/")
    public R editActivity(@Validated(ValidationGroups.EditActivityGroup.class) @RequestBody Activity activity) {
        try {
            Integer result = activityService.editActivity(activity);
            return result >= 1 ? R.SUCCESS(result) : R.FAIL("Edit marketing activity failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Edit marketing activity failed!Please try again!");
        }
    }

    /**
     * Delete/Restore activities by ids
     * @param query query object, including list of deleted/restored ids and isDeletedValue
     * @return R.success or R.fail
     */
    @PreAuthorize("hasAuthority('activity:delete')")
    @PutMapping("/updateActivities")
    public R updateActivitiesByIds(@Validated @RequestBody BatchUpdateQuery query) {
        try {
            Integer result = activityService.updateActivitiesByIds(query.getIds(), query.getIsDeletedValue());
            return result == query.getIds().size() ? R.SUCCESS() : R.FAIL("Delete/Restore users failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Delete/Restore users failed!Please try again!");
        }
    }

    /**
     * Get activities with id, name and region only
     * @return List of activities, encapsulated into R: data
     */
    @GetMapping("/activities")
    public R getActivities() {
        return R.SUCCESS(activityService.getActivities());
    }

    /**
     * Get all activities without pagination
     * @param query query object
     * @return List of all activities without pagination, encapsulated into R: data
     */
    @GetMapping("/all")
    public R getAllActivitiesWithoutPagination(GetActivitiesQuery query) {
        return R.SUCCESS(activityService.getAllActivitiesWithoutPagination(query));
    }
}
