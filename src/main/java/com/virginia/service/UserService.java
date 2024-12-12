package com.virginia.service;

import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.pojo.User;
import com.virginia.query.GetUsersQuery;
import com.virginia.query.UserRoleQuery;

import java.util.List;

public interface UserService {
    // Query user data by page, with searching parameters(optional)
    PageBean getAllUsers(GetUsersQuery query);

    // Add new user
    Integer addUser(UserRoleQuery query);

    // Edit user
    Integer editUser(UserRoleQuery query);

    // Remove/Restore users by userIds
    Integer updateUsersByIds(List<Integer> ids, Integer accountEnabledValue);

    // Query all users with id and login_act only
    List<User> getOwners();
}
