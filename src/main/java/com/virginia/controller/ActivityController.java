package com.virginia.controller;

import com.virginia.pojo.PageBean;
import com.virginia.query.GetActivitiesQuery;
import com.virginia.result.R;
import com.virginia.service.impl.ActivityServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
