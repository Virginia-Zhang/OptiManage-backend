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
        // Send loginAct and password to the user by email
        String subject = "Welcome to OptiManage CRM system | 欢迎使用优客易CRM管理系统 | オプティマネージ CRMシステムへようこそ";
        // 请使用中英日三种语言发送邮件，要求三种语言版本里都要包含账号和密码信息
        String content = String.format(
                "尊敬的用户，您好！\n\n您的账号已创建成功，详情如下：\n账号：%s\n密码：%s\n\n请及时登录并修改密码以确保账号安全。\n\n谢谢！",
                user.getLoginAct(), password
        );
        emailUtils.sendSimpleEmail(user.getEmail(), subject, content);

        // Encrypt the password
        user.setLoginPwd(passwordEncoder.encode(password));
        // Set accountNoExpired, credentialsNoExpired, accountNoLocked, and accountEnabled to 1
        user.setAccountNoExpired(1);
        user.setCredentialsNoExpired(1);
        user.setAccountNoLocked(1);
        user.setAccountEnabled(1);
        // Set createTime
        user.setCreateTime(new Date());
        // Get the current logged in user id from SecurityContextHolder and set createBy
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof MyUserDetails) {
            MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
            user.setCreateBy(userDetails.getUser().getId());
        }
        // Insert data
        return userMapper.insertSelective(user);
    }
}
