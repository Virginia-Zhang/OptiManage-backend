package com.virginia.service.impl;

import com.virginia.constants.Constants;
import com.virginia.pojo.MyUserDetails;
import com.virginia.query.LoginQuery;
import com.virginia.result.R;
import com.virginia.service.LoginService;
import com.virginia.utils.JWTUtils;
import com.virginia.utils.RedisUtils;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImpl implements LoginService {
    @Resource
    private AuthenticationManager authenticationManager;

    @Override
    public R checkLogin(LoginQuery loginQuery) {
        String loginAct = loginQuery.getLoginAct();
        String loginPwd = loginQuery.getLoginPwd();
        Boolean rememberMe = loginQuery.getRememberMe();

        System.out.println("loginPwd: "+loginPwd);

        UsernamePasswordAuthenticationToken authenticationToken = UsernamePasswordAuthenticationToken.unauthenticated(loginAct, loginPwd);

        try {
            // Call the authenticate method of authentication manager to start authentication.
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            if (authentication.isAuthenticated()) {
                // If the authentication is successful, success data is returned, and the authentication object is saved in the security context object for use by subsequent filters.
                SecurityContextHolder.getContext().setAuthentication(authentication);
                /**
                 *Add the logic after successful authentication
                 *Obtain the authenticated user information, put it into a token, and return it to the front end
                 *Save authenticated user information to redis
                 */
                // Get authenticated user information
                Object principal = authentication.getPrincipal();
                MyUserDetails userDetails = (MyUserDetails) principal;
                // Get the user id from user details and put it into the redis key later.
                Integer userId = userDetails.getUser().getId();
                // Create a hash map object, store it in the user details object, and then store the map object in the token.
                Map<String, Object> payload = new HashMap<>();
                payload.put("user", userDetails);
                // Generate token and pass in payload. If Remember Me is selected, the expiration time is 7 days, otherwise it is 30 minutes
                String token = JWTUtils.generateToken(payload, rememberMe ? Constants.EXPIRE_TIME : Constants.DEFAULT_EXPIRE_TIME);
                // Store the token in redis, the key is crm:user:login:user id, and the value is token
                RedisUtils.setValue(Constants.REDIS_JWT_KEY + userId, token);
                // Set expiration time for redis
                RedisUtils.expire(Constants.REDIS_JWT_KEY + userId, rememberMe ? Constants.EXPIRE_TIME : Constants.DEFAULT_EXPIRE_TIME, TimeUnit.MILLISECONDS);

                // Return data to the front end, including token
                return R.SUCCESS(token);
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }
        // Authentication failed, failure data returned
        return R.FAIL("Login failed!");
    }
}
