package com.virginia.controller;

import com.virginia.query.LoginQuery;
import com.virginia.result.R;
import com.virginia.service.LoginService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public R login(@RequestBody LoginQuery loginQuery) {
        return loginService.checkLogin(loginQuery);
    }
}
