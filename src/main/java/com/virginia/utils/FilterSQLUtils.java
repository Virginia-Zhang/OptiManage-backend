package com.virginia.utils;

import com.virginia.annotation.DataFilterByRegionAnnotation;
import com.virginia.annotation.DataFilterByUserAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

/**
 * Assembling data filtering sql statement for data filtering aspects
 * @author Virginia
 */
public class FilterSQLUtils {
    public static String getFilterSQL(JoinPoint joinPoint, Object field, String annotationName) {
        // Get the target method object by join point
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        // Get the table alias and table field value in the annotation
        String tableAlias = "";
        String tableField = "";
        // Get the annotation on the method
        if("DataFilterByUserAnnotation".equals(annotationName)){
            DataFilterByUserAnnotation dataFilterByUserAnnotation = method.getDeclaredAnnotation(DataFilterByUserAnnotation.class);
            tableAlias = dataFilterByUserAnnotation.tableAlias();
            tableField = dataFilterByUserAnnotation.tableField();
        }else if("DataFilterByRegionAnnotation".equals(annotationName)){
            DataFilterByRegionAnnotation dataFilterByRegionAnnotation = method.getDeclaredAnnotation(DataFilterByRegionAnnotation.class);
            tableAlias = dataFilterByRegionAnnotation.tableAlias();
            tableField = dataFilterByRegionAnnotation.tableField();
        }
        // Assembling data filtering sql statement
        // and ta.owner_id = 2, for example
        return " and " + tableAlias + "." + tableField + " = " + field ;
    }
}
