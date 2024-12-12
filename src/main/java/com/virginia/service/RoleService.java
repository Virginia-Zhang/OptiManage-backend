package com.virginia.service;

import com.virginia.pojo.Role;

import java.util.List;

/**
 * Role-related service interfaces
 * @author Virginia
 */
public interface RoleService {
    List<Role> getRoleListByUserId(Integer userId);
}
