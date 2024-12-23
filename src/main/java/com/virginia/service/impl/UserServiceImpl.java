package com.virginia.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.virginia.manager.UserRoleManager;
import com.virginia.mapper.UserMapper;
import com.virginia.pojo.PageBean;
import com.virginia.pojo.User;
import com.virginia.query.DataFilterQuery;
import com.virginia.query.GetUsersQuery;
import com.virginia.query.UserRoleQuery;
import com.virginia.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserRoleManager userRoleManager;

    /**
     *  Query user data by page, with searching parameters(optional)
     *  @param query query object
     *  @return pageBean, contains the total number of records and current page data
     */
    @Override
    public PageBean getAllUsers(GetUsersQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<User> userList = userMapper.selectAll(query);
        Page<User> pageInfo = (Page<User>) userList;
        return new PageBean(pageInfo.getTotal(), pageInfo.getResult());
    }

    @Override
    public Integer addUser(UserRoleQuery query) {
        return userRoleManager.addUserAndRole(query);
    }

    @Override
    public Integer editUser(UserRoleQuery query) {
        return userRoleManager.editUserAndRole(query);
    }

    @Override
    public Integer updateUsersByIds(List<Integer> ids, Integer accountEnabledValue) {
        return  userRoleManager.updateUsersAndRoles(ids, accountEnabledValue);
    }

    @Override
    public List<User> getOwners() {
        return userMapper.selectOwners(DataFilterQuery.builder().build());
    }
}
