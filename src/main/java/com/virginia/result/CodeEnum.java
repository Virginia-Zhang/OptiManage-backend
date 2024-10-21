package com.virginia.result;

import lombok.*;

// code and message enum
@Getter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public enum CodeEnum {
    SUCCESS(200, "success"),

    FAIL(500, "failed"),

    TOKEN_IS_EMPTY(901, "token is empty"),

    TOKEN_IS_WRONG(902, "token error"),

    TOKEN_IS_EXPIRED(903, "token is expired"),

    TOKEN_NOT_MATCH(904, "token doesn't match"),

    USER_LOGOUT(200, "logout success"),

    DATA_ACCESS_EXCEPTION(500,"database operation failed"),

    ACCESS_DENIED(500, "no authorities");


    // result code
    private int code;

    // result message
    @NonNull
    private String msg;
}
