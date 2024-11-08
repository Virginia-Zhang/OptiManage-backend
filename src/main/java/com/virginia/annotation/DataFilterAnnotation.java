package com.virginia.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *Custom annotations for data filtering/data permission control
 * @author Virginia
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataFilterAnnotation {
    // table alias
    String tableAlias();
    // field in table
    String tableField();
}
