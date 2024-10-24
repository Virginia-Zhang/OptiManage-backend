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

    // 获取用户信息
    @GetMapping("/info")
    public R getUserInfo() {
        MyUserDetails userDetails = userServiceImpl.getUserInfo();
        // 删除userDetails中user.loginPwd字段再返回给前端
        userDetails.getUser().setLoginPwd(null);
        return R.SUCCESS(userDetails);
    }

}
