package com.virginia.aop;

import com.virginia.annotation.DataFilterAnnotation;
import com.virginia.pojo.MyUserDetails;
import com.virginia.query.DataFilterQuery;
import com.virginia.utils.UserUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;

/**
 *Data permission control aspect
 * @author Virginia
 */
@Aspect
@Component
public class DataFilterAspect {
    @Before("@annotation(com.virginia.annotation.DataFilterAnnotation)")
    public void dataFilter(JoinPoint joinPoint) throws Throwable {
        // Get current user information from security context holder
        MyUserDetails userInfo = UserUtils.getLoggedInUserInfo();
        // Get user id
        assert userInfo != null;
        Integer userId = userInfo.getUser().getId();
        // Get role list
        List<String> roleList = userInfo.getRoleList();
        // Get filter sql
        String filterSQL = getFilterSQL(joinPoint, userId);
        // Determine whether the user is admin, if not, assemble the data filtering sql statement
        if (!roleList.contains("admin")) {
            // Get the target method parameters, which is DataFilterQuery object
            DataFilterQuery query = (DataFilterQuery) joinPoint.getArgs()[0];
            // Assign the value of filterSQL to filterSQL property of the query object.
            query.setFilterSQL(filterSQL);
        }
    }

    // Assembling data filtering sql statement
    private static String getFilterSQL(JoinPoint joinPoint, Integer userId) {
        // Get the target method object by join point
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // Get  the annotation on the method
        DataFilterAnnotation dataFilterAnnotation = method.getDeclaredAnnotation(DataFilterAnnotation.class);
        // Get the values in the annotation
        String tableAlias = dataFilterAnnotation.tableAlias();
        String tableField = dataFilterAnnotation.tableField();
        // Assembling data filtering sql statement
        // and ta.owner_id = 2
        return " and " + tableAlias + "." + tableField + " = " + userId;
    }
}
