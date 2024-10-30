package com.virginia.controller;

import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.result.R;
import com.virginia.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserServiceImpl userServiceImpl;

    // Get user information

    @GetMapping("/info")
    public R getUserInfo() {
        MyUserDetails userDetails = userServiceImpl.getUserInfo();
        // Delete the user.login pwd field in user details and return it to the front end

        userDetails.getUser().setLoginPwd(null);
        return R.SUCCESS(userDetails);
    }

    /**
     *Query user data by page and return
     * @param page current page number
     * @param pageSize The number of data items displayed on each page
     * @return paging data, the format is: {total: 100, rows: [{}, {}, ...]}, encapsulated into R: data
     */
    @GetMapping("/list")
    public R getAllUsers(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean users = userServiceImpl.getAllUsers(page, pageSize);
        return R.SUCCESS(users);
    }

}
