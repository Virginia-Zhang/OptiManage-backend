package com.virginia.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
    *User role relationship entity
    */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer roleId;

    private Integer isDeleted;

    private static final long serialVersionUID = 1L;
}