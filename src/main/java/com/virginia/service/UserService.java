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

    // remove users by userIds
    Integer removeUsersByIds(List<Integer> ids);
}
