package com.virginia.result;

import com.virginia.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Encapsulate data returned to front end after login
 * @author Virginia
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {
    private String token;
    private List<String> roleList;
    private List<String> permissionList;
    private User userInfo;
}
