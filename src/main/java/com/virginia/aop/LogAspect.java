package com.virginia.aop;

import com.alibaba.fastjson2.JSONObject;
import com.virginia.mapper.OperateLogMapper;
import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.OperateLog;
import com.virginia.utils.UserUtils;
import jakarta.annotation.Resource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 *Log aspect, recording all additions, deletions and modifications
 * @author Virginia
 */
@Aspect
@Component
public class LogAspect {
    @Resource
    private OperateLogMapper operateLogMapper;

    // Log notification method
    @Around("@annotation(com.virginia.annotation.LogAnnotation)")
    public Object log(ProceedingJoinPoint joinPoint){
        try {
            // Obtain the logged-in user's id and loginAct from security context
            MyUserDetails userInfo = UserUtils.getLoggedInUserInfo();
            assert userInfo != null;
            String loginAct = userInfo.getUser().getLoginAct();
            Integer userId = userInfo.getUser().getId();
            // Operation time, the value is the current datetime
            LocalDateTime operateTime = LocalDateTime.now();
            // The class name of the operation method
            String className = joinPoint.getTarget().getClass().getName();
            // Operation method name
            String methodName = joinPoint.getSignature().getName();
            // Operation method parameters
            String methodParams = Arrays.toString(joinPoint.getArgs());
            // Execute the target method and obtain the returned value
            Object result = joinPoint.proceed();
            // Convert returned value to json string using fastJson
            String resultValue = JSONObject.toJSONString(result);

            // Encapsulate the operation data into OperateLog object
            OperateLog operateLog = new OperateLog(null, userId, loginAct, operateTime, className, methodName, methodParams, resultValue);
            // Insert into t_operate_log table
            operateLogMapper.insertSelective(operateLog);

            return result;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }
    }
}
