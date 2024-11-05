package com.virginia.service.impl;

import com.virginia.mapper.RoleMapper;
import com.virginia.mapper.UserMapper;
import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.Role;
import com.virginia.pojo.User;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
// overwrite UserDetailsService
public class MyUserDetailsService implements UserDetailsManager {
    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public void createUser(UserDetails user) {

    }

    @Override
    public void updateUser(UserDetails user) {

    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return false;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.selectByLoginAct(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        // Query the user's role list based on the user ID
        List<Role> roles = roleMapper.selectRoleListByUserId(user.getId());
        // Use stream to change the generic type of roleList to String, and then return the new collection
        List<String> roleList = roles.stream().map(Role::getRole).toList();
        List<String> permissionList = List.of();
        return new MyUserDetails(user, roleList, permissionList);
    }
}
