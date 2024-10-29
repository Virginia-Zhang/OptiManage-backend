package com.virginia.filter;

import com.alibaba.fastjson2.JSON;
import com.virginia.constants.Constants;
import com.virginia.pojo.MyUserDetails;
import com.virginia.result.CodeEnum;
import com.virginia.result.R;
import com.virginia.utils.JSONUtils;
import com.virginia.utils.JWTUtils;
import com.virginia.utils.RedisUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Skip login interface
        if (request.getRequestURI().equals("/api/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        // Get token from request header
        String auth = request.getHeader("Authorization");
        // The Auth field is empty and the result is that the token is empty.

        if (auth == null || !auth.startsWith("Bearer ")) {
            JSONUtils.print(R.FAIL(CodeEnum.TOKEN_IS_EMPTY), response);
            return;
        }
        String token = auth.split(" ")[1];
        // If Token is empty, return the result.TOKEN_IS_EMPTY
        if (token == null || token.isEmpty()) {
            JSONUtils.print(R.FAIL(CodeEnum.TOKEN_IS_EMPTY), response);
            return;
        }
        // Token is invalid, and the result of invalid token is returned.
        if (!JWTUtils.validateToken(token)) {
            JSONUtils.print(R.FAIL(CodeEnum.TOKEN_IS_INVALID), response);
            return;
        }
        // Parse the user information from the token and get the user id
        String userJSON = (String) JWTUtils.getClaimFromToken(token, "user");
        // Convert user json to MyUserDetails object
        MyUserDetails userDetails = JSON.parseObject(userJSON, MyUserDetails.class);
        Integer userId = userDetails.getUser().getId();
        // Get token from redis
        String redisToken = (String) RedisUtils.getValue(Constants.REDIS_JWT_KEY + userId);
        // Redis token does not exist, and the result of token expiration is returned.
        if (redisToken == null) {
            JSONUtils.print(R.FAIL(CodeEnum.TOKEN_IS_EXPIRED), response);
            return;
        }
        // Redis token is inconsistent with token, and the result of token mismatch is returned.
        if (!redisToken.equals(token)) {
            JSONUtils.print(R.FAIL(CodeEnum.TOKEN_NOT_MATCH), response);
            return;
        }
        // After the verification is passed, the user information is put into the security context.
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getUser().getLoginPwd(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // refresh token
        threadPoolTaskExecutor.execute(() -> {
           // Get rememberMe from the request header
           Boolean rememberMe = Boolean.valueOf(request.getHeader("rememberMe"));
           // If rememberMe is true, extend the expire time of token in redis to 7 days, otherwise extend to 30 minutes
            RedisUtils.expire(Constants.REDIS_JWT_KEY + userId, rememberMe ? Constants.EXPIRE_TIME : Constants.DEFAULT_EXPIRE_TIME, TimeUnit.MILLISECONDS);
        });

        // release
        filterChain.doFilter(request, response);
    }
}
