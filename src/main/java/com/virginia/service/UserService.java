package com.virginia.service;

import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.pojo.User;

import java.util.List;

public interface UserService {
    // Get user information
    MyUserDetails getUserInfo();

    // Query user data by page and return
    PageBean getAllUsers(Integer page, Integer pageSize);

    // Add new user
    Integer addUser(User user);

    // Edit user
    Integer editUser(User user);

    // Remove/Restore users by userIds
    Integer updateUsersByIds(List<Integer> ids, Integer accountEnabledValue);

    // Query deleted users data by page and return
    PageBean getDeletedUsers(Integer page, Integer pageSize);

    // Query all users with id and login_act only
    List<User> getOwners();
}
