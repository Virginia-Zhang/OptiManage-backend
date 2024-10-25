package com.virginia.controller;

import com.virginia.pojo.MyUserDetails;
import com.virginia.result.R;
import com.virginia.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
