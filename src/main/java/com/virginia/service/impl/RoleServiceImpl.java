package com.virginia.service.impl;

import com.virginia.mapper.RoleMapper;
import com.virginia.pojo.Role;
import com.virginia.service.RoleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;


    @Override
    public List<Role> getRoleListByUserId(Integer userId) {
        return roleMapper.selectRoleListByUserId(userId);
    }
}
