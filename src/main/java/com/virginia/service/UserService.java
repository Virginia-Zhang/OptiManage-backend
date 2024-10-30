package com.virginia.service;

import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;

public interface UserService {
    // Get user information
    MyUserDetails getUserInfo();

    // 分页查询用户数据并返回
    PageBean getAllUsers(Integer page, Integer pageSize);
}
