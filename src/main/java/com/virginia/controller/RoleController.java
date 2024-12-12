package com.virginia.controller;

import com.virginia.result.R;
import com.virginia.service.impl.RoleServiceImpl;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Role-related interfaces
 * @author Virginia
 */
@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Resource
    private RoleServiceImpl roleService;

    /**
     * Get role list by user id
     * @param userId user id
     * @return role list, encapsulated into R:data
     */
    @GetMapping("/list")
    public R getRoleListByUserId(@NotNull Integer userId) {
        return R.SUCCESS(roleService.getRoleListByUserId(userId));
    }
}
