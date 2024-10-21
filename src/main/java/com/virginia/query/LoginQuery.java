package com.virginia.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Encapsulate the login data submitted by frontend.
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginQuery {
    // loginAccount
    private String loginAct;
    // loginPassword
    private String loginPwd;
    // rememberMe
    private Boolean rememberMe;
}
