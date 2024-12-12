package com.virginia.manager;

import com.virginia.annotation.LogAnnotation;
import com.virginia.mapper.RoleMapper;
import com.virginia.mapper.UserMapper;
import com.virginia.mapper.UserRoleMapper;
import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.Role;
import com.virginia.pojo.User;
import com.virginia.pojo.UserRole;
import com.virginia.query.UserRoleQuery;
import com.virginia.utils.EmailUtils;
import com.virginia.utils.PasswordUtils;
import com.virginia.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class UserRoleManager {
    @Resource
    private UserMapper userMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private EmailUtils emailUtils;

    @Resource
    private UserRoleMapper userRoleMapper;

    @Resource
    private RoleMapper roleMapper;

    /**
     * Add user and role
     * @param query UserRoleQuery object, including user and role information
     * @return rows affected, 1 if success, otherwise exception thrown
     */
    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    public Integer addUserAndRole(UserRoleQuery query) {
        // First add user to the database
        int result1 = addUser(query);
        /**
         *Then assign roles to User
         *Traverse the roleIds list and insert the relationship between roles and the user into the t_user_role table in sequence
         */
        // The final execution result of assigning roles to the user, which is the number of rows affected
        AtomicInteger result2 = new AtomicInteger();
        query.getRoleIds().forEach(roleId -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(query.getId());
            userRole.setRoleId(roleId);
            int i = userRoleMapper.insertSelective(userRole);
            if (i == 1) result2.addAndGet(i);
        });
        if(result1 != 1 || result2.get() != query.getRoleIds().size()){
            throw new RuntimeException("Failed to add user and role! Please try again later!");
        }
        return 1;
    }

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

    /**
     * Edit user and role
     * @param query UserRoleQuery object, including user and role information
     * @return rows affected, 1 if success, otherwise exception thrown
     */
    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    public Integer editUserAndRole(UserRoleQuery query) {
        // Modify the user first
        int result1 = editUser(query);
        /**
         *Then modify the role list corresponding to the user
         *Compare the roleIds in query object with the user's original role list, find out the new roles and deleted roles, and perform delete and add operations in the t_user_role table
         */
        // Get the user's original role list
        List<Role> oldRoleList = roleMapper.selectRoleListByUserId(query.getId());
        // Process the old role list so that only ID is left for each item to get ready for later comparison.
        List<Integer> oldRoleIds = oldRoleList.stream().map(Role::getId).toList();
        // Get roleIds in query object and compare it with oldRoleIds to find out the new roles and deleted roles.
        List<Integer> newRoleIds = query.getRoleIds();
        List<Integer> addedRoleIds = newRoleIds.stream().filter(roleId -> !oldRoleIds.contains(roleId)).toList();
        List<Integer> deletedRoleIds = oldRoleIds.stream().filter(roleId -> !newRoleIds.contains(roleId)).toList();
        // Traverse addedRoleIds and deletedRoleIds, and perform the operations of adding and deleting UserRole respectively.
        AtomicInteger result2 = new AtomicInteger(0);
        AtomicInteger result3 = new AtomicInteger(0);
        if(!addedRoleIds.isEmpty()){
            addedRoleIds.forEach(roleId -> {
                UserRole userRole = new UserRole();
                userRole.setUserId(query.getId());
                userRole.setRoleId(roleId);
                int i = userRoleMapper.insertSelective(userRole);
                if (i == 1) result2.addAndGet(i);
            });
        }
        if(!deletedRoleIds.isEmpty()){
            deletedRoleIds.forEach(roleId -> {
                int i = userRoleMapper.deleteByUserIdAndRoleId(query.getId(), roleId);
                if (i == 1) result3.addAndGet(i);
            });
        }
        if(result1 != 1 || result2.get() != addedRoleIds.size() || result3.get() != deletedRoleIds.size()){
            throw new RuntimeException("Failed to edit user and role! Please try again later!");
        }
        return 1;
    }

    public Integer editUser(User user) {
        // Set editTime
        user.setEditTime(LocalDateTime.now());
        // Get the current logged-in user id from SecurityContextHolder and set editBy
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        user.setEditBy(loggedInUserInfo.getUser().getId());
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
