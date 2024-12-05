package com.virginia.service;

import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.pojo.User;
import com.virginia.query.GetUsersQuery;

import java.util.List;

public interface UserService {
    // Get user information
    MyUserDetails getUserInfo();

    // Query user data by page, with searching parameters(optional)
    PageBean getAllUsers(GetUsersQuery query);

    // Add new user
    Integer addUser(User user);

    // Edit user
    Integer editUser(User user);

    // Remove/Restore users by userIds
    Integer updateUsersByIds(List<Integer> ids, Integer accountEnabledValue);

    // Query all users with id and login_act only
    List<User> getOwners();
}
