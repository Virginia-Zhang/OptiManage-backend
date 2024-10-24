package com.virginia.filter;

import com.alibaba.fastjson2.JSON;
import com.virginia.constants.Constants;
import com.virginia.pojo.MyUserDetails;
import com.virginia.result.CodeEnum;
import com.virginia.result.R;
import com.virginia.utils.JSONUtils;
import com.virginia.utils.JWTUtils;
import com.virginia.utils.RedisUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 放行login接口
        if (request.getRequestURI().equals("/api/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        // 从请求头中获取token
        String auth = request.getHeader("Authorization");
        // auth字段为空，返回token为空的结果
        if (auth == null || !auth.startsWith("Bearer ")) {
            JSONUtils.print(R.FAIL(CodeEnum.TOKEN_IS_EMPTY), response);
            ;
            return;
        }
        String token = auth.split(" ")[1];
        // token为空，返回token为空的结果
        if (token == null || token.isEmpty()) {
            JSONUtils.print(R.FAIL(CodeEnum.TOKEN_IS_EMPTY), response);
            return;
        }
        // token无效，返回token无效的结果
        if (!JWTUtils.validateToken(token)) {
            JSONUtils.print(R.FAIL(CodeEnum.TOKEN_IS_INVALID), response);
            return;
        }
        // 从token中解析出用户信息，拿到userId
        String userJSON = (String) JWTUtils.getClaimFromToken(token, "user");
        // 把userJSON转为MyUSerDetails类型的对象
        MyUserDetails userDetails = JSON.parseObject(userJSON, MyUserDetails.class);
        Integer userId = userDetails.getUser().getId();
        // 从redis中获取token
        String redisToken = (String) RedisUtils.getValue(Constants.REDIS_JWT_KEY + userId);
        // redisToken不存在，返回token过期的结果
        if (redisToken == null) {
            JSONUtils.print(R.FAIL(CodeEnum.TOKEN_IS_EXPIRED), response);
            return;
        }
        // redisToken与token不一致，返回token不匹配的结果
        if (!redisToken.equals(token)) {
            JSONUtils.print(R.FAIL(CodeEnum.TOKEN_NOT_MATCH), response);
            return;
        }
        // 校验通过，把用户信息放入SecurityContext中
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getUser().getLoginPwd(), userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        // 放行
        filterChain.doFilter(request, response);
    }
}
