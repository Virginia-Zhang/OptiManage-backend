package com.virginia.controller;

import com.virginia.pojo.PageBean;
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
     * Query activity data by page and return
     * @param page current page number
     * @param pageSize The number of data items displayed on each page
     * @return paging data, the format is: {total: 100, rows: [{}, {}, ...]}, encapsulated into R: data
     */
    @GetMapping("/list")
    public R getAllActivities(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean activities = activityService.getAllActivities(page, pageSize);
        return R.SUCCESS(activities);
    }
}
