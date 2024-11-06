package com.virginia.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
    *Addition, deletion and modification operation log records
    */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateLog implements Serializable {
    /**
    *Record ID
    */
    private Integer id;

    /**
    *Operation user ID
    */
    private Integer operateUser;

    /**
    *Operation user account
    */
    private String operateUserLoginAct;

    /**
    *Operating time
    */
    private LocalDateTime operateTime;

    /**
    *Operation class name
    */
    private String className;

    /**
    *Operation method name
    */
    private String methodName;

    /**
    *Method parameters
    */
    private String methodParams;

    /**
    *Method returned value
    */
    private String returnValue;

    private static final long serialVersionUID = 1L;
}