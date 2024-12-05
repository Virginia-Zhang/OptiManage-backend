package com.virginia.controller;

import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.pojo.User;
import com.virginia.query.BatchUpdateQuery;
import com.virginia.query.GetUsersQuery;
import com.virginia.query.SelectAllQuery;
import com.virginia.result.R;
import com.virginia.service.impl.UserServiceImpl;
import com.virginia.validation.ValidationGroups;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserServiceImpl userServiceImpl;

    /**
     * Get logged-in user information
     * @return user details, encapsulated into R: data
     */
    @GetMapping("/info")
    public R getLoggedInUserInfo() {
        MyUserDetails userDetails = userServiceImpl.getUserInfo();
        // Delete the user.login pwd field in user details and return it to the front end
        userDetails.getUser().setLoginPwd(null);
        return R.SUCCESS(userDetails);
    }

    /**
     *Query user data by page, with searching parameters(optional)
     * @param query query object
     * @return paging data, the format is: {total: 100, rows: [{}, {}, ...]}, encapsulated into R: data
     */
    @GetMapping("/list")
    public R getAllUsers(GetUsersQuery query) {
        PageBean users = userServiceImpl.getAllUsers(query);
        return R.SUCCESS(users);
    }

    /**
     * Add user
     * @param user User object
     * @return number of rows affected, encapsulated into R: data
     */
    @PostMapping("/")
    public R addUser(@Validated(ValidationGroups.AddUserGroup.class) @RequestBody User user) {
        try {
            Integer result = userServiceImpl.addUser(user);
            return result >= 1 ? R.SUCCESS(result) : R.FAIL("Add user failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Add user failed!Please try again!");
        }
    }

    /**
     * Edit user
     * @param user User object
     * @return number of rows affected, encapsulated into R: data
     */
    @PutMapping("/")
    public R editUser(@Validated(ValidationGroups.EditUserGroup.class) @RequestBody User user) {
        try {
            Integer result = userServiceImpl.editUser(user);
            return result >= 1 ? R.SUCCESS(result) : R.FAIL("Edit user failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Edit user failed!Please try again!");
        }
    }

    /**
     * Delete/Restore users by ids
     * @param query query object, including list of deleted/restored user ids and accountEnabledValue
     * @return R.success or R.fail
     */
    @PutMapping("/updateUsers")
    public R updateUsersByIds(@RequestBody BatchUpdateQuery query) {
        try {
            Integer result = userServiceImpl.updateUsersByIds(query.getIds(), query.getIsDeletedValue());
            return result == query.getIds().size() ? R.SUCCESS() : R.FAIL("Delete/Restore users failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Delete/Restore users failed!Please try again!");
        }
    }

    /**
     * Query all marketing activity owners (users with id and login_act only) for display in the drop-down list
     * @return List of owners, encapsulated into R: data
     */
    @GetMapping("/owners")
    public R getOwners() {
        List<User> owners = userServiceImpl.getOwners();
        return R.SUCCESS(owners);
    }
}
