package com.virginia.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.virginia.annotation.LogAnnotation;
import com.virginia.mapper.UserMapper;
import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.pojo.User;
import com.virginia.query.GetUsersQuery;
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
    public Integer addUser(User user) {
        // Generate a random password
        String password = PasswordUtils.generateRandomPassword();
        // Set preferredLanguage according to user.getPreferredLanguage()
        String preferredLanguage = switch (user.getPreferredLanguage()) {
            case 2 -> "zh";
            case 3 -> "ja";
            default -> "en";
        };
        // Send loginAct and password to the new user by email
        emailUtils.sendLocalizedTemplateEmail(user.getEmail(), user.getLoginAct(), password, preferredLanguage);

        // Encrypt the password
        user.setLoginPwd(passwordEncoder.encode(password));
        // Set accountNoExpired, credentialsNoExpired, accountNoLocked, and accountEnabled to 1
        user.setAccountNoExpired(1);
        user.setCredentialsNoExpired(1);
        user.setAccountNoLocked(1);
        user.setAccountEnabled(1);
        // Set createTime
        user.setCreateTime(LocalDateTime.now());
        // Get the current logged-in user id from SecurityContextHolder and set createBy
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        user.setCreateBy(loggedInUserInfo.getUser().getId());
        // Insert data
        return userMapper.insertSelective(user);
    }

    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    @Override
    public Integer editUser(User user) {
        // Set editTime
        user.setEditTime(LocalDateTime.now());
        // Get the current logged-in user id from SecurityContextHolder and set editBy
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        user.setEditBy(loggedInUserInfo.getUser().getId());
        return userMapper.updateByPrimaryKeySelective(user);
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
