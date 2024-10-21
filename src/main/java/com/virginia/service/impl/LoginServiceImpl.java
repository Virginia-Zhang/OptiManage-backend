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
import org.springframework.security.authentication.BadCredentialsException;
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
            // 调用AuthenticationManager的authenticate方法，开始认证
            Authentication authentication = authenticationManager.authenticate(authenticationToken);
            if (authentication.isAuthenticated()) {
                // 认证成功，返回成功数据，并把authentication对象保存到安全上下文对象中，以供后面的过滤器使用
                SecurityContextHolder.getContext().setAuthentication(authentication);
                /**
                 * 完善认证成功之后的逻辑
                 * 获取认证用户信息，封装到token中，返回给前端
                 * 把认证用户信息保存到redis
                 */
                // 获取认证用户信息
                Object principal = authentication.getPrincipal();
                MyUserDetails userDetails = (MyUserDetails) principal;
                // 从userDetails中拿到userId，等会放到redis的key中
                Integer userId = userDetails.getUser().getId();
                // 创建HashMap对象，存入userDetails对象，再把Map对象存入到token中
                Map<String, Object> payload = new HashMap<>();
                payload.put("user", userDetails);
                // 生成token，传入payload。如果选择了记住我，过期时间是7天，否则是30分钟
                String token = JWTUtils.generateToken(payload, rememberMe ? Constants.EXPIRE_TIME : Constants.DEFAULT_EXPIRE_TIME);
                // 把token存入redis，key是crm:user:login:userId，value是token
                RedisUtils.setValue(Constants.REDIS_JWT_KEY + userId, token);
                // 给redis设置过期时间
                RedisUtils.expire(Constants.REDIS_JWT_KEY + userId, rememberMe ? Constants.EXPIRE_TIME : Constants.DEFAULT_EXPIRE_TIME, TimeUnit.MILLISECONDS);

                // 向前端返回数据，包含token
                return R.SUCCESS(token);
            }
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("authentication failed");
        }
        // 认证失败，返回失败数据
        return R.FAIL("Login failed!");
    }
}
