package com.virginia.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Customize an verification annotation to verify data in BigDecimal type, checking whether it is a non-negative number and have up to two digits after the decimal point.
 * @author Virginia
 */

// Create a verification annotation and specify the validator
@Constraint(validatedBy = CostBigDecimalValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidCost {
    String message() default "Cost must be a valid number with at most two decimal places";  // Default error message
    Class<?>[] groups() default {};  // Used for group verification
    Class<? extends Payload>[] payload() default {};  // Used to carry additional data
}

