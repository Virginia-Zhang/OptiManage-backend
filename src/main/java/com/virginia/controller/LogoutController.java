package com.virginia.controller;

import com.virginia.result.CodeEnum;
import com.virginia.result.R;
import com.virginia.service.LogoutService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LogoutController {
    @Resource
    private LogoutService logoutService;

    @PostMapping("/logout")
    public R logout() {
        logoutService.logout();
        return R.SUCCESS(CodeEnum.USER_LOGOUT);
    }
}
