package com.virginia.controller;

import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.pojo.User;
import com.virginia.result.R;
import com.virginia.service.impl.UserServiceImpl;
import jakarta.annotation.Resource;
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
     *Query user data by page and return
     * @param page current page number
     * @param pageSize The number of data items displayed on each page
     * @return paging data, the format is: {total: 100, rows: [{}, {}, ...]}, encapsulated into R: data
     */
    @GetMapping("/list")
    public R getAllUsers(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer pageSize) {
        PageBean users = userServiceImpl.getAllUsers(page, pageSize);
        return R.SUCCESS(users);
    }

    /**
     * Add user
     * @param user User object
     * @return number of rows affected, encapsulated into R: data
     */
    @PostMapping("/")
    public R addUser(@RequestBody User user) {
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
    public R editUser(@RequestBody User user) {
        try {
            Integer result = userServiceImpl.editUser(user);
            return result >= 1 ? R.SUCCESS(result) : R.FAIL("Edit user failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Edit user failed!Please try again!");
        }
    }

    /**
     * Delete users by ids
     * @param ids List of deleted user ids
     * @return R.success or R.fail
     */
    @DeleteMapping("/")
    public R removeUsersByIds(@RequestBody List<Integer> ids) {
        try {
            Integer result = userServiceImpl.removeUsersByIds(ids);
            return result == ids.size() ? R.SUCCESS() : R.FAIL("Delete users failed!Please try again!");
        } catch (Exception e) {
            e.printStackTrace();
            return R.FAIL("Delete users failed!Please try again!");
        }
    }
}
