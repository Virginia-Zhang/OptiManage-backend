package com.virginia.aop;

import com.virginia.pojo.MyUserDetails;
import com.virginia.query.DataFilterQuery;
import com.virginia.utils.FilterSQLUtils;
import com.virginia.utils.UserUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 *Data permission control aspect, used to filter data by userId
 * @author Virginia
 */
@Aspect
@Component
public class DataFilterByUserAspect {
    @Before("@annotation(com.virginia.annotation.DataFilterByUserAnnotation)")
    public void dataFilter(JoinPoint joinPoint) {
        // Get current user information from security context holder
        MyUserDetails userInfo = UserUtils.getLoggedInUserInfo();
        // Get user id
        assert userInfo != null;
        Integer userId = userInfo.getUser().getId();
        // Get role list
        List<String> roleList = userInfo.getRoleList();
        // Get filter sql, which is used to filter the data by userId
        String filterSQLByUser = FilterSQLUtils.getFilterSQL(joinPoint, userId, "DataFilterByUserAnnotation");
        // Determine whether the user is a regular user, if so, assemble the data filtering sql statement and assign it to the filterSQL property of the query object.
        if (!roleList.contains("super admin") && !roleList.contains("super financing") && !roleList.contains("admin") && !roleList.contains("financing") && !roleList.contains("sales manager") && !roleList.contains("marketing manager")) {
            // Get the target method parameters, which is DataFilterQuery object
            DataFilterQuery query = (DataFilterQuery) joinPoint.getArgs()[0];
            // Assign the value of filterSQL to filterSQL property of the query object.
            query.setFilterSQL(filterSQLByUser);
        }
    }
}
