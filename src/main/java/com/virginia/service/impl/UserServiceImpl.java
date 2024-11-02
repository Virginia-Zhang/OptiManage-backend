package com.virginia.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.virginia.mapper.UserMapper;
import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.pojo.User;
import com.virginia.service.UserService;
import com.virginia.utils.EmailUtils;
import com.virginia.utils.PasswordUtils;
import com.virginia.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private EmailUtils emailUtils;

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

    @Override
    public Integer addUser(User user) {
        // Generate a random password
        String password = PasswordUtils.generateRandomPassword(6, 16);
        // Set preferred language according to region
        String preferredLanguage;
        // If region is China, set preferred language to Chinese
        if (user.getRegion() == 1) {
            preferredLanguage = "zh";
            user.setPreferredLanguage(2);
        } else if (user.getRegion() == 2) {
            // If region is Japan, set preferred language to Japanese
            preferredLanguage = "ja";
            user.setPreferredLanguage(3);
        } else {
            // If region is others, set preferred language to English
            preferredLanguage = "en";
            user.setPreferredLanguage(1);
        }
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
        user.setCreateTime(new Date());
        // Get the current logged-in user id from SecurityContextHolder and set createBy
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        user.setCreateBy(loggedInUserInfo.getUser().getId());
        // Insert data
        return userMapper.insertSelective(user);
    }

    @Override
    public Integer editUser(User user) {
        // Set editTime
        user.setEditTime(new Date());
        // Get the current logged-in user id from SecurityContextHolder and set editBy
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        user.setEditBy(loggedInUserInfo.getUser().getId());
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
