package com.virginia.controller;

import com.virginia.pojo.PageBean;
import com.virginia.pojo.User;
import com.virginia.query.BatchUpdateQuery;
import com.virginia.query.GetUsersQuery;
import com.virginia.query.UserRoleQuery;
import com.virginia.result.R;
import com.virginia.service.impl.UserServiceImpl;
import com.virginia.validation.ValidationGroups;
import jakarta.annotation.Resource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Resource
    private UserServiceImpl userServiceImpl;

    /**
     *Query user data by page, with searching parameters(optional)
     * @param query query object
     * @return paging data, the format is: {total: 100, rows: [{}, {}, ...]}, encapsulated into R: data
     */
    @PreAuthorize("hasAuthority('user:list')")
    @GetMapping("/list")
    public R getAllUsers(GetUsersQuery query) {
        PageBean users = userServiceImpl.getAllUsers(query);
        return R.SUCCESS(users);
    }

    /**
     * Add user and assign role to the newly added user
     * @param query UserRoleQuery object, including user and role information
     * @return number of rows affected, encapsulated into R: data
     */
    @PreAuthorize("hasAuthority('user:add')")
    @PostMapping("/")
    public R addUser(@Validated(ValidationGroups.AddUserGroup.class) @RequestBody UserRoleQuery query) {
        try {
            Integer result = userServiceImpl.addUser(query);
            return result >= 1 ? R.SUCCESS(result) : R.FAIL("Add user failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Add user failed!Please try again!");
        }
    }

    /**
     * Edit user and user's roles
     * @param query UserRoleQuery object, including user and role information
     * @return number of rows affected, encapsulated into R: data
     */
    @PreAuthorize("hasAuthority('user:edit')")
    @PutMapping("/")
    public R editUser(@Validated(ValidationGroups.EditUserGroup.class) @RequestBody UserRoleQuery query) {
        try {
            Integer result = userServiceImpl.editUser(query);
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
    @PreAuthorize("hasAuthority('user:delete')")
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
