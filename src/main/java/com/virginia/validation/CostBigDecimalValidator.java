package com.virginia.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

// Create a validator and implement ConstraintValidator
public class CostBigDecimalValidator implements ConstraintValidator<ValidCost, BigDecimal> {
    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        // Check: whether it is an integer or up to two decimal places
        return value.scale() <= 2 && value.stripTrailingZeros().scale() <= 2;
    }
}

