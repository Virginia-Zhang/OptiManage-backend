package com.virginia.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Wrap the login data submitted by frontend.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginQuery {
    private String loginAct;
    private String loginPwd;
    private Boolean rememberMe;
}
