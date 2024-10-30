package com.virginia.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.virginia.mapper.UserMapper;
import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.pojo.User;
import com.virginia.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;


    @Override
    public MyUserDetails getUserInfo() {
        // Get user information from security context and return

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof MyUserDetails) {
            return (MyUserDetails) authentication.getPrincipal();
        }
        return null;
    }

    /**
     *Query user data by page and return
     * @param page current page number
     * @param pageSize How many pieces of data to display on each page
     * @return pageBean, contains the total number of records and current page data
     */
    @Override
    public PageBean getAllUsers(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<User> userList = userMapper.selectAll();
        Page<User> pageInfo = (Page<User>) userList;
        return new PageBean(pageInfo.getTotal(), pageInfo.getResult());
    }
}
