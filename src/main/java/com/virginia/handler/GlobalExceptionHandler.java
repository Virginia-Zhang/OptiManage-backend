package com.virginia.handler;

import com.virginia.result.CodeEnum;
import com.virginia.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.validation.FieldError;

import java.util.stream.Collectors;

/**
 *Global exception handling class
 * @author Virginia
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     *Handle exceptions
     * @param e Various exceptions
     * @return R.FAIL
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        e.printStackTrace();
        return R.FAIL(e.getMessage());
    }

    /**
     * Handle method arguments validation exceptions
     * @param e Method argument validation exception
     * @return exception message, encapsulated in R.FAIL
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleValidationExceptions(MethodArgumentNotValidException e) {
        // Get the binding result, which contains validation errors for all fields
        BindingResult bindingResult = e.getBindingResult();

        // Get the validation error information for all fields and concat it into a single string
        String errorMessages = bindingResult.getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)  // Get the message defined when the field verification fails
                .collect(Collectors.joining("\\n"));  // Separate each error message with a newline


        log.error("Validation failed: {}", errorMessages);
        // Return the spliced error message
        return R.FAIL(errorMessages); // Error message returned to the front end (in string form, encapsulated into R.msg)

    }

    /**
     *Handle insufficient permission exceptions
     * @param e Insufficient permission exception
     *return R.FAIL(CodeEnum.ACCESS_DENIED)
     */
    @ExceptionHandler(AccessDeniedException.class)
    public R handleAccessDeniedException(Exception e) {
        e.printStackTrace();
        return R.FAIL(CodeEnum.ACCESS_DENIED);
    }
}
