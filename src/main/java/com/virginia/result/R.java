package com.virginia.result;

// encapsulating the result data returned to frontend
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class R {
    // Indicates the return status code, for example, 200 for success, 500 for failure.
    private int code;

    // Indicates the return message, for example, "User login status has expired", "Request parameter format is incorrect"......
    private String msg;

    // Indicates the return data
    private Object data;

    public static R SUCCESS() {
        return R.builder()
                .code(CodeEnum.SUCCESS.getCode())
                .msg(CodeEnum.SUCCESS.getMsg())
                .build();
    }

    public static R SUCCESS(int code, String msg) {
        return R.builder()
                .code(code)
                .msg(msg)
                .build();
    }

    public static R SUCCESS(Object data) {
        return R.builder()
                .code(CodeEnum.SUCCESS.getCode())
                .msg(CodeEnum.SUCCESS.getMsg())
                .data(data)
                .build();
    }

    public static R SUCCESS(CodeEnum codeEnum) {
        return R.builder()
                .code(CodeEnum.SUCCESS.getCode())
                .msg(codeEnum.getMsg())
                .build();
    }

    public static R FAIL() {
        return R.builder()
                .code(CodeEnum.FAIL.getCode())
                .msg(CodeEnum.FAIL.getMsg())
                .build();
    }

    public static R FAIL(String msg) {
        return R.builder()
                .code(CodeEnum.FAIL.getCode())
                .msg(msg)
                .build();
    }

    public static R FAIL(CodeEnum codeEnum) {
        return R.builder()
                .code(codeEnum.getCode())
                .msg(codeEnum.getMsg())
                .build();
    }
}
