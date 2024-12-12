package com.virginia.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.virginia.annotation.LogAnnotation;
import com.virginia.manager.UserRoleManager;
import com.virginia.mapper.UserMapper;
import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.pojo.User;
import com.virginia.query.GetUsersQuery;
import com.virginia.query.UserRoleQuery;
import com.virginia.service.UserService;
import com.virginia.utils.EmailUtils;
import com.virginia.utils.PasswordUtils;
import com.virginia.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private EmailUtils emailUtils;

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

    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    @Override
    public Integer addUser(UserRoleQuery query) {
        return userRoleManager.addUserAndRole(query);
    }

    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    @Override
    public Integer editUser(UserRoleQuery query) {
        return userRoleManager.editUserAndRole(query);
    }

    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    @Override
    public Integer updateUsersByIds(List<Integer> ids, Integer accountEnabledValue) {
        // Update edit_time and edit_by at the same time
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        return userMapper.updateUsersByIds(ids, accountEnabledValue, LocalDateTime.now(), loggedInUserInfo.getUser().getId());
    }

    @Override
    public List<User> getOwners() {
        return userMapper.selectOwners();
    }
}
