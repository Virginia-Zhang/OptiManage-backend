package com.virginia.handler;

import com.virginia.result.CodeEnum;
import com.virginia.result.R;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *Global exception handling class
 * @author Virginia
 */
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
     *Handle insufficient permission exceptions
     * @param e Insufficient permission exception
     *return R.FAIL(CodeEnum.ACCESS_DENIED)
     */
    public R handleAccessDeniedException(Exception e) {
        e.printStackTrace();
        return R.FAIL(CodeEnum.ACCESS_DENIED);
    }
}
