package com.virginia.service.impl;

import com.virginia.constants.Constants;
import com.virginia.pojo.MyUserDetails;
import com.virginia.service.LogoutService;
import com.virginia.utils.RedisUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class LogoutServiceImpl implements LogoutService {
    @Override
    public void logout() {
        // Get MyUserDetails object from security context and retrieve the user id
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer userId = userDetails.getUser().getId();
        // Delete token in redis
        RedisUtils.removeValue(Constants.REDIS_JWT_KEY + userId);
        // Clear security context
        SecurityContextHolder.clearContext();
    }
}
